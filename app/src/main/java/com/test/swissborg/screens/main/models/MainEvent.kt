package com.test.swissborg.screens.main.models

import com.test.swissborg.screens.main.util.FilterCurrency


sealed class MainEvent {
    object EnterScreen : MainEvent()
    object ReloadScreen : MainEvent()
    data class Filter(val filter: FilterCurrency) : MainEvent()
}
