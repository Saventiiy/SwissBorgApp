package com.test.swissborg.screens.main.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.test.swissborg.ui.theme.SwissBorgTheme


@Composable
fun MainViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = Color.Gray
        )
    }
}

@Preview
@Composable
fun MainViewLoading_Preview() {
    SwissBorgTheme {
        MainViewLoading()
    }
}