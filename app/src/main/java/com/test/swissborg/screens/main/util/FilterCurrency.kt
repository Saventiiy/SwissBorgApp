package com.test.swissborg.screens.main.util

sealed class FilterCurrency {
    object Default : FilterCurrency()
    object FilterByName : FilterCurrency()
    object FilterByDescendingName : FilterCurrency()
    object FilterByPrice : FilterCurrency()
    object FilterByDescendingPrice : FilterCurrency()
}