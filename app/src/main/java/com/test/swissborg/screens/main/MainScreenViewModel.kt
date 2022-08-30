package com.test.swissborg.screens.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.swissborg.base.EventHandler
import com.test.swissborg.data.util.ApplicationError
import com.test.swissborg.data.util.onError
import com.test.swissborg.data.util.onSuccess
import com.test.swissborg.domain.CurrencyUseCase
import com.test.swissborg.screens.main.models.MainEvent
import com.test.swissborg.screens.main.models.MainViewState
import com.test.swissborg.screens.main.util.FilterCurrency
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: CurrencyUseCase) : ViewModel(),
    EventHandler<MainEvent> {

    private var _job: Job? = null
    private val _filter = mutableStateOf<FilterCurrency>(FilterCurrency.Default)
    private val _mainViewState: MutableLiveData<MainViewState> =
        MutableLiveData(MainViewState.Loading)
    val filter: State<FilterCurrency> = _filter
    val mainViewState: LiveData<MainViewState> = _mainViewState

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
            MainEvent.EnterScreen -> checkPlatformStatus(onReloadClick = true)
            else -> {}
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Error) {
        when (event) {
            MainEvent.ReloadScreen -> checkPlatformStatus()
            else -> {}
        }
    }

    private fun reduce(event: MainEvent, currentState: MainViewState.Display) {
        when (event) {
            MainEvent.EnterScreen -> fetchData()
            MainEvent.ReloadScreen -> fetchData(onReloadClick = true)
            is MainEvent.Filter -> {
                _filter.value = event.filter
                fetchData()
            }
        }
    }

    private fun checkPlatformStatus(onReloadClick: Boolean = false) {
        _mainViewState.postValue(MainViewState.Loading)
        viewModelScope.launch {
            useCase.checkPlatformStatus()
                .onSuccess {
                    if (it) {
                        _mainViewState.postValue(MainViewState.Error(ApplicationError.Server))
                    } else {
                        fetchData(onReloadClick)
                    }
                }
                .onError { _mainViewState.postValue(MainViewState.Error(it)) }
        }
    }

    private fun fetchData(onReloadClick: Boolean = false) {
        if (onReloadClick) {
            _mainViewState.postValue(MainViewState.Loading)
        }
        _job = viewModelScope.launch {
            while (isActive) {
                useCase.getListCurrency()
                    .onSuccess {
                        _mainViewState.postValue(MainViewState.Display(it))
                    }
                    .onError {
                        _mainViewState.postValue(MainViewState.Error(it))
                    }
                delay(5000)
            }
        }
    }

    fun stopListUpdates() {
        _job?.cancel()
        _job = null
    }
}