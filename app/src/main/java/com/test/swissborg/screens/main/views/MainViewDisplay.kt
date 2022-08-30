package com.test.swissborg.screens.main.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.swissborg.R
import com.test.swissborg.data.model.Currency
import com.test.swissborg.screens.main.models.MainViewState
import com.test.swissborg.screens.main.util.FilterCurrency
import com.test.swissborg.ui.theme.SwissBorgTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainViewDisplay(
    viewState: MainViewState.Display,
    onFilterChange: (FilterCurrency) -> Unit
) {
    Scaffold(topBar = { TopBar(onFilterChange = onFilterChange) }) {
        val items = viewState.items
        LazyColumn {
            items(items.size) { item ->
                CurrencyCard(item = items[item])
            }
        }
    }
}

@Composable
fun TopBar(onFilterChange: (FilterCurrency) -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onFilterChange.invoke(FilterCurrency.Default)
                }) {
                    Text(text = stringResource(id = R.string.clear_filter))
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onFilterChange.invoke(FilterCurrency.FilterByName)
                }) {
                    Text(text = stringResource(id = R.string.filter_by_name_a_z))
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onFilterChange.invoke(FilterCurrency.FilterByDescendingName)
                }) {
                    Text(text = stringResource(id = R.string.filter_by_name_z_a))
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onFilterChange.invoke(FilterCurrency.FilterByPrice)
                }) {
                    Text(text = stringResource(id = R.string.filter_by_price_from_low_to_high))
                }
                DropdownMenuItem(onClick = {
                    showMenu = false
                    onFilterChange.invoke(FilterCurrency.FilterByDescendingPrice)
                }) {
                    Text(text = stringResource(id = R.string.filter_by_price_from_high_to_low))
                }
            }
        }
    )
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
                text = item.frr.toString(),
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