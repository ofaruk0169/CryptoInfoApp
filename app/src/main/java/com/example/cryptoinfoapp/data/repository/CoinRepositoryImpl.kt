package com.example.cryptoinfoapp.data.repository

import com.example.cryptoinfoapp.data.remote.CoinPaprikaApi
import com.example.cryptoinfoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoinfoapp.data.remote.dto.CoinDto
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}