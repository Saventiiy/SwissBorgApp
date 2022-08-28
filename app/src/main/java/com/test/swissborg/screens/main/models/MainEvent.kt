package com.test.swissborg.screens.main.models

sealed class MainEvent {
    object EnterScreen : MainEvent()
    object ReloadScreen : MainEvent()
}
