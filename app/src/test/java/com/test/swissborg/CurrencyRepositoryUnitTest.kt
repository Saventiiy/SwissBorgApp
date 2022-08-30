package com.test.swissborg

import com.test.swissborg.data.util.onSuccess
import com.test.swissborg.domain.CurrencyUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CurrencyRepositoryUnitTest {

    @Mock
    private lateinit var currencyUseCase: CurrencyUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun loadCurrency() = runBlocking {
        currencyUseCase.getListCurrency().onSuccess {
            assertEquals(18, it.size)
        }
        return@runBlocking
    }

    @Test
    fun checkPlatformStatus() = runBlocking {
        currencyUseCase.checkPlatformStatus().onSuccess {
            assertEquals(false, it)
        }
        return@runBlocking
    }
}