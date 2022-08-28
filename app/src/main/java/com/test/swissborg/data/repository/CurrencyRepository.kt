package com.test.swissborg.data.repository

import com.google.gson.JsonArray
import com.test.swissborg.data.remote.CurrencyApi
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val api: CurrencyApi) :
    CurrencyRepositoryInterface {

    override suspend fun getListCurrency(): JsonArray = api.getListCurrency()

}