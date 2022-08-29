package com.test.swissborg.data.remote

import com.google.gson.JsonArray
import retrofit2.http.GET

interface CurrencyApi {

    //https://api-pub.bitfinex.com/v2/tickers?symbols=ALL
    @GET("tickers?symbols=tBTCUSD,tETHUSD,tCHSB:USD,tLTCUSD,tXRPUSD,tDSHUSD, tRRTUSD,tEOSUSD,tSANUSD,tDATUSD,tSNTUSD,tDOGE:USD,tLUNA:USD,tMATIC:USD,tNEXO:USD,tOCEAN:USD,tBEST:USD, tAAVE:USD,tPLUUSD,tFILUSD")
    suspend fun getListCurrency(): JsonArray
}