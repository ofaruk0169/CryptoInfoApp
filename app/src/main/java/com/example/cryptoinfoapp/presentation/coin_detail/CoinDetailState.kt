package com.example.cryptoinfoapp.presentation.coin_detail

import com.example.cryptoinfoapp.domain.model.Coin
import com.example.cryptoinfoapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
