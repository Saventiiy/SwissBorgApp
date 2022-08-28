package com.test.swissborg.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.swissborg.data.util.onError
import com.test.swissborg.data.util.onSuccess
import com.test.swissborg.domain.CurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: CurrencyUseCase) : ViewModel() {

    private var job: Job? = null
    fun getList() {
        job = viewModelScope.launch {
            while (isActive) {
                useCase.getListCurrency()
                    .onSuccess {
                        Log.e("RESPONSE", it.toString())
                    }
                    .onError {
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