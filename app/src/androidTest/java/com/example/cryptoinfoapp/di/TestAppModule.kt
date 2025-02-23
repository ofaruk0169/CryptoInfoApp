package com.example.cryptoinfoapp.di

import com.example.cryptoinfoapp.common.Constants
import com.example.cryptoinfoapp.data.remote.CoinPaprikaApi
import com.example.cryptoinfoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.mockk.mockk
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        // Provide a mock implementation for testing
        return mockk()
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        // Provide a mock or fake repository for testing
        return mockk()
    }
}
