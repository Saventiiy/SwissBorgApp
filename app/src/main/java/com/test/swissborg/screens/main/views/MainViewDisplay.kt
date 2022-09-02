package com.test.swissborg.screens.main.views

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.swissborg.R
import com.test.swissborg.data.model.Currency
import com.test.swissborg.screens.main.models.MainViewState
import com.test.swissborg.screens.main.util.FilterCurrency
import com.test.swissborg.ui.theme.Bombay
import com.test.swissborg.ui.theme.HeavyMetal
import com.test.swissborg.ui.theme.HippieBlue
import com.test.swissborg.ui.theme.SwissBorgTheme
import java.math.BigDecimal

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainViewDisplay(
    viewState: MainViewState.Display,
    onFilterChange: (FilterCurrency) -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(topBar = { TopBar(onFilterChange = onFilterChange, state = textState) }) {
        val items =
            viewState.items.filter { it.symbol!!.contains(textState.value.text, ignoreCase = true) }
        LazyColumn {
            items(items.size) { item ->
                CurrencyCard(item = items[item])
            }
        }
    }
}

@Composable
fun TopBar(onFilterChange: (FilterCurrency) -> Unit, state: MutableState<TextFieldValue>) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = { SearchView(state = state) },
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
            .height(140.dp)
            .padding(vertical = 8.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .height(64.dp)
                    .width(64.dp)
            )
            Text(
                text = item.symbol.toString(),
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .weight(2f)
                    .padding(start = 8.dp)
            )
            Column(modifier = Modifier.weight(1.5f)) {
                Text(
                    text = BigDecimal(item.frr.toString()).toString().plus(" $"),
                    color = HippieBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(vertical = 4.dp))

                //To be honest, I'm not completely sure that the percentages are calculated correctly, the documentation says that you need to multiply by 100, but then you get too large numbers
                Text(
                    text = BigDecimal("%.3f".format(item.dailyChangeRelative?.div(100))).toString()
                        .plus("%"),
                    color = Bombay,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = HeavyMetal,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun MainView_Preview() {
    SwissBorgTheme {
    }
}