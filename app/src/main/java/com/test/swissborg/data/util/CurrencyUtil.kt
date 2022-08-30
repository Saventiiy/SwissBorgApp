package com.test.swissborg.data.util

import com.test.swissborg.R

fun String?.cut() = this?.replace("t", "")?.replace("USD", "")?.replace(":", "")

fun String?.getIcon() =
    when(this){
        "BTC" -> R.drawable.ic_btc
        "ETH" -> R.drawable.ic_eth
        "LTC" -> R.drawable.ic_ltc
        "XRP" -> R.drawable.ic_xrp
        "EOS" -> R.drawable.ic_eos
        "DOGE" -> R.drawable.ic_doge
        "AAVE" -> R.drawable.ic_aave
        else -> R.drawable.ic_coin
    }