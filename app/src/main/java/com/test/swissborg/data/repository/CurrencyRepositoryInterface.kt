package com.test.swissborg.data.repository

import com.test.swissborg.data.model.Currency
import com.test.swissborg.data.util.Result

interface CurrencyRepositoryInterface {
    suspend fun getListCurrency(): Result<List<Currency>>
}