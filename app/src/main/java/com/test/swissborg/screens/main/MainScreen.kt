package com.test.swissborg.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.test.swissborg.screens.main.models.MainEvent
import com.test.swissborg.screens.main.models.MainViewState
import com.test.swissborg.screens.main.views.MainViewDisplay
import com.test.swissborg.screens.main.views.MainViewError
import com.test.swissborg.screens.main.views.MainViewLoading

@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
    val viewState = viewModel.mainViewState.observeAsState()
    when (val state = viewState.value) {
        is MainViewState.Loading -> MainViewLoading()
        is MainViewState.Error -> MainViewError(
            error = state.error,
            onReloadClick = {
                viewModel.obtainEvent(
                    MainEvent.ReloadScreen
                )
            }
        )
        is MainViewState.Display -> MainViewDisplay(viewState = state)
        else -> throw NotImplementedError("Unexpected state")
    }

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(event = MainEvent.EnterScreen)
    })
}