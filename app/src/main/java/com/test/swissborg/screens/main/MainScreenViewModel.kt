package com.test.swissborg.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.swissborg.domain.CurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val useCase: CurrencyUseCase) : ViewModel() {

    fun getList() = viewModelScope.launch {
        Log.e("RESPONSE", useCase.getListCurrency().toString())
    }
}