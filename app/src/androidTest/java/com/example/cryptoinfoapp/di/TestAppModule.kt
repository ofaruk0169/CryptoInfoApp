package com.example.cryptoinfoapp.di

import com.example.cryptoinfoapp.data.remote.CoinPaprikaApi
import com.example.cryptoinfoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoinfoapp.data.remote.dto.CoinDto
import com.example.cryptoinfoapp.data.remote.dto.Whitepaper
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import kotlinx.coroutines.runBlocking
import io.mockk.coEvery
import io.mockk.mockk
import javax.inject.Singleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    // Provide MockWebServer instance
    @Provides
    @Singleton
    fun provideMockWebServer(): MockWebServer {
        return MockWebServer()
    }

    @Provides
    @Singleton
    fun provideMockCoinPaprikaApi(mockWebServer: MockWebServer): CoinPaprikaApi {
        // Use MockWebServer URL for testing instead of the real API URL
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))  // Use the mocked server's base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        // Mock the CoinRepository interface
        return mockk<CoinRepository>(relaxed = true)
    }
}


