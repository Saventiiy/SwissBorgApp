package com.test.swissborg.data.model

import com.google.gson.JsonArray
import com.google.gson.JsonNull
import com.test.swissborg.data.util.cut
import com.test.swissborg.data.util.getIcon

data class Currency(
    val symbol: String?,
    val frr: Float?,
    val bid: Float?,
    val bidPeriod: Int?,
    val bidSize: Float?,
    val ask: Float?,
    val askPeriod: Int?,
    val askSize: Float?,
    val dailyChange: Float?,
    val dailyChangeRelative: Float?,
    val lastPrice: Float?,
    val volume: Float?,
    val high: Float?,
    val low: Float?,
    val frrAmountAvailable: Float?,
    val icon: Int
)

fun JsonArray.toCurrency() =
    this.map {
        val size = it.asJsonArray.size()
        Currency(
            symbol = it.asJsonArray[0].asString.cut(),
            frr = it.asJsonArray[1].asFloat,
            bid = it.asJsonArray[2].asFloat,
            bidPeriod = it.asJsonArray[3].asInt,
            bidSize = it.asJsonArray[4].asFloat,
            ask = it.asJsonArray[5].asFloat,
            askPeriod = it.asJsonArray[6].asInt,
            askSize = it.asJsonArray[7].asFloat,
            dailyChange = it.asJsonArray[8].asFloat,
            dailyChangeRelative = it.asJsonArray[9].asFloat,
            lastPrice = it.asJsonArray[10].asFloat,
            volume = if (size > 11) it.asJsonArray[11].asFloat else null,
            high = if (size > 12) it.asJsonArray[12].asFloat else null,
            low = if (size > 13) it.asJsonArray[13].asFloat else null,
            frrAmountAvailable = if (size > 14 && it.asJsonArray[14] != JsonNull.INSTANCE) it.asJsonArray[14].asFloat else null,
            icon = it.asJsonArray[0].asString.cut().getIcon()
        )
    }
