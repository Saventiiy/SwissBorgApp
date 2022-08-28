package com.test.swissborg.screens.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.swissborg.base.EventHandler
import com.test.swissborg.data.util.onError
import com.test.swissborg.data.util.onSuccess
import com.test.swissborg.domain.CurrencyUseCase
import com.test.swissborg.screens.main.models.MainEvent
import com.test.swissborg.screens.main.models.MainViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: CurrencyUseCase) : ViewModel(),
    EventHandler<MainEvent> {

    private var job: Job? = null
    private val _mainViewState: MutableLiveData<MainViewState> =
        MutableLiveData(MainViewState.Loading)
    var mainViewState: MutableLiveData<MainViewState> = _mainViewState

    override fun obtainEvent(event: MainEvent) {
        when (val currentState = _mainViewState.value) {
            is MainViewState.Loading -> reduce(event, currentState)
            is MainViewState.Error -> reduce(event, currentState)
            is MainViewState.Display -> reduce(event, currentState)
            else -> {}
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Loading) {
        when (event) {
            MainEvent.EnterScreen -> fetchData()
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Error) {
        when (event) {
            MainEvent.ReloadScreen -> fetchData(onReloadClick = true)
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Display) {
        when (event) {
            MainEvent.EnterScreen -> fetchData()
            MainEvent.ReloadScreen -> fetchData(onReloadClick = true)
        }
    }

    private fun fetchData(onReloadClick: Boolean = false) {
        if (onReloadClick) {
            _mainViewState.postValue(MainViewState.Loading)
        }
        job = viewModelScope.launch {
            while (isActive) {
                useCase.getListCurrency()
                    .onSuccess {
                        _mainViewState.postValue(MainViewState.Display(it))
                        Log.e("RESPONSE", it.toString())
                    }
                    .onError {
                        _mainViewState.postValue(MainViewState.Error(it))
                        Log.e("RESPONSE", it.toString())
                    }
                delay(5000)
            }
        }
    }

    fun stopListUpdates() {
        job?.cancel()
        job = null
    }
}