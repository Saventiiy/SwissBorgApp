package com.test.swissborg.di

import com.test.swissborg.data.remote.CurrencyApi
import com.test.swissborg.data.repository.CurrencyRepository
import com.test.swissborg.domain.CurrencyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl(): String = "https://api-pub.bitfinex.com/v2/"

    @Provides
    @Singleton
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideCurrencyApi(retrofit: Retrofit) = retrofit.create(CurrencyApi::class.java)

    @Provides
    @Singleton
    fun provideCurrencyRepository(currencyApi: CurrencyApi) = CurrencyRepository(currencyApi)

    @Provides
    @Singleton
    fun provideCurrencyUseCase(currencyRepository: CurrencyRepository) = CurrencyUseCase(currencyRepository)
}