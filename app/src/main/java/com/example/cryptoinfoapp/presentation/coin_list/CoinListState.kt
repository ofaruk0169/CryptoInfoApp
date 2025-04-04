package com.example.cryptoinfoapp.presentation.coin_list

import com.example.cryptoinfoapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
