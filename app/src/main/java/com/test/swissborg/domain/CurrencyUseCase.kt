package com.test.swissborg.domain

import com.test.swissborg.data.model.Currency
import com.test.swissborg.data.repository.CurrencyRepository
import com.test.swissborg.data.util.Result
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val repository: CurrencyRepository) {

    suspend fun getListCurrency(): Result<List<Currency>> = repository.getListCurrency()
}