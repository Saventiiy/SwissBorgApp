package com.test.swissborg.data.util

import com.test.swissborg.R

fun ApplicationError.message() =
    when (this) {
        //Common errors
        ApplicationError.Generic -> R.string.generic_error
        ApplicationError.NoInternetConnection -> R.string.noInternetConnection_error

        //Http errors
        ApplicationError.BadRequest -> R.string.badRequest_error
        ApplicationError.NotFound -> R.string.notFound_error
        ApplicationError.Unauthorized -> R.string.unauthorized_error
        ApplicationError.TimeOut -> R.string.timeout_error
        ApplicationError.Server -> R.string.server_error
    }