package com.test.swissborg.screens.main.util

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.test.swissborg.data.model.Currency

fun List<Currency>.filterCurrency(filter: FilterCurrency?): List<Currency> =
    when (filter) {
        FilterCurrency.Default -> this
        FilterCurrency.FilterByName -> this.sortedBy { it.symbol }
        FilterCurrency.FilterByDescendingName -> this.sortedByDescending { it.symbol }
        FilterCurrency.FilterByPrice -> this.sortedBy { it.frr }
        FilterCurrency.FilterByDescendingPrice -> this.sortedByDescending { it.frr }
        else -> this
    }

fun <T> SnapshotStateList<T>.swapList(newList: List<T>) {
    clear()
    addAll(newList)
}