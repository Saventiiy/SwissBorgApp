package com.test.swissborg.data.repository

import com.google.gson.JsonArray

interface CurrencyRepositoryInterface {
    suspend fun getListCurrency(): JsonArray
}