package com.test.swissborg.screens.main.models

import com.test.swissborg.data.model.Currency
import com.test.swissborg.data.util.ApplicationError

sealed class MainViewState {
    object Loading : MainViewState()
    data class Error(val error: ApplicationError) : MainViewState()
    data class Display(val items: List<Currency>) : MainViewState()
}
