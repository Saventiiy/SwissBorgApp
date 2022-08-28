package com.test.swissborg.screens.main.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.swissborg.data.model.Currency
import com.test.swissborg.screens.main.models.MainViewState
import com.test.swissborg.ui.theme.SwissBorgTheme

@Composable
fun MainViewDisplay(viewState: MainViewState.Display) {
    val currencyList = viewState.items
    LazyColumn {
        items(currencyList.size) { item ->
            CurrencyCard(item = currencyList[item])
        }
    }
}

@Composable
fun CurrencyCard(item: Currency) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            Text(
                text = item.symbol.toString(),
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier.weight(
                    1f
                )
            )
            Text(
                text = item.bid.toString(),
                color = Color.Black,
                fontSize = 24.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun MainView_Preview() {
    SwissBorgTheme {
    }
}