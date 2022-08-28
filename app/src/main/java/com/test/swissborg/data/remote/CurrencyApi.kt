package com.test.swissborg.data.remote

import com.google.gson.JsonArray
import retrofit2.http.GET

interface CurrencyApi {

    //https://api-pub.bitfinex.com/v2/tickers?symbols=ALL
    @GET("tickers?symbols=ALL")
    suspend fun getListCurrency(): JsonArray
}