package com.test.swissborg.screens.main.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.swissborg.R
import com.test.swissborg.data.util.ApplicationError
import com.test.swissborg.data.util.message
import com.test.swissborg.ui.theme.SwissBorgTheme

@Composable
fun MainViewError(
    error: ApplicationError,
    onReloadClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    imageVector = Icons.Filled.Warning,
                    tint = Color.DarkGray,
                    contentDescription = "Error loading items"
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp, bottom = 24.dp),
                    text = stringResource(id = error.message()),
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )

                Button(
                    onClick = onReloadClick,
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.refresh),
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MainViewError_Preview() {
    SwissBorgTheme {
        MainViewError(error = ApplicationError.Generic, onReloadClick = {})
    }
}