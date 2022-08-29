package com.test.swissborg.data.repository

import com.test.swissborg.data.model.Currency
import com.test.swissborg.data.model.toCurrency
import com.test.swissborg.data.remote.CurrencyApi
import com.test.swissborg.data.util.Result
import com.test.swissborg.data.util.safeApiCall
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val api: CurrencyApi) :
    CurrencyRepositoryInterface {

    override suspend fun checkPlatformStatus(): Result<Boolean> =
        safeApiCall { api.checkPlatformStatus().asBoolean }

    override suspend fun getListCurrency(): Result<List<Currency>> =
        safeApiCall { api.getListCurrency().toCurrency() }
}