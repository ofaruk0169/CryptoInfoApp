package com.example.cryptoinfoapp.presentation.coin_list

import android.util.Log
import com.example.cryptoinfoapp.data.remote.CoinPaprikaApi
import com.example.cryptoinfoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoinfoapp.data.remote.dto.CoinDto
import com.example.cryptoinfoapp.data.remote.dto.Links
import com.example.cryptoinfoapp.data.remote.dto.LinksExtended
import com.example.cryptoinfoapp.data.remote.dto.Stats
import com.example.cryptoinfoapp.data.remote.dto.Tag
import com.example.cryptoinfoapp.data.remote.dto.TeamMember
import com.example.cryptoinfoapp.data.remote.dto.Whitepaper
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TestCoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        Log.d("TestRepo", "Attempting to get coins from API")
        try {
            // Use Dispatchers.IO to avoid using the Main dispatcher which could be interrupted
            val result = withContext(Dispatchers.IO) {
                try {
                    val response = api.getCoins()
                    Log.d("TestRepo", "API call successful!")
                    response
                } catch (e: Exception) {
                    Log.e("TestRepo", "Error in API call: ${e.javaClass.simpleName}: ${e.message}")
                    e.printStackTrace()
                    throw e
                }
            }
            Log.d("TestRepo", "Success! Got ${result.size} coins: ${result.take(3).joinToString { it.name }}")
            return result
        } catch (e: Exception) {
            Log.e("TestRepo", "Error getting coins: ${e.javaClass.simpleName}: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        Log.d("TestRepo", "Attempting to get coin details for: $coinId")
        try {
            // Use Dispatchers.IO to avoid using the Main dispatcher which could be interrupted
            val result = withContext(Dispatchers.IO) {
                try {
                    val response = api.getCoinById(coinId)
                    Log.d("TestRepo", "API call successful!")
                    response
                } catch (e: Exception) {
                    Log.e("TestRepo", "Error in API call: ${e.javaClass.simpleName}: ${e.message}")
                    e.printStackTrace()
                    throw e
                }
            }
            Log.d("TestRepo", "Success! Got details for ${result.name}")
            return result
        } catch (e: Exception) {
            Log.e("TestRepo", "Error getting coin details: ${e.javaClass.simpleName}: ${e.message}")
            e.printStackTrace()
            throw e
        }
    }
}