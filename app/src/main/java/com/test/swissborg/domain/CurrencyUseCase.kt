package com.test.swissborg.domain

import com.test.swissborg.data.model.Currency
import com.test.swissborg.data.model.toCurrency
import com.test.swissborg.data.repository.CurrencyRepository
import javax.inject.Inject
import javax.inject.Singleton

class CurrencyUseCase @Inject constructor(private val repository: CurrencyRepository) {

    suspend fun getListCurrency(): List<Currency> = repository.getListCurrency().toCurrency()
}