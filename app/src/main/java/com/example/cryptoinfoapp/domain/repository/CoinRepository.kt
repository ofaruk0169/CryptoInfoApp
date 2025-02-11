package com.example.cryptoinfoapp.domain.repository

import com.example.cryptoinfoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoinfoapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}