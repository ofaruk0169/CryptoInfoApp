package com.example.cryptoinfoapp.di

import android.util.Log
import com.example.cryptoinfoapp.common.Constants
import com.example.cryptoinfoapp.data.remote.CoinPaprikaApi
import com.example.cryptoinfoapp.data.repository.CoinRepositoryImpl
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import com.example.cryptoinfoapp.presentation.coin_list.FakeCoinRepository
import com.example.cryptoinfoapp.presentation.coin_list.TestCoinRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class]
)
object TestAppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d("OkHttpClient", message)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    @Named("test_api_base_url")
    fun provideBaseUrl(): String {
        return "http://10.0.2.2:8080"
    }

    @Provides
    @Singleton
    fun providePaprikaApi(
        okHttpClient: OkHttpClient,
        @Named("test_api_base_url") baseUrl: String
    ): CoinPaprikaApi {
        Log.d("TestAppModule", "Creating API with base URL: $baseUrl")
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(): CoinRepository {
        // Use the fake repository to avoid network issues
        return FakeCoinRepository()
    }
}