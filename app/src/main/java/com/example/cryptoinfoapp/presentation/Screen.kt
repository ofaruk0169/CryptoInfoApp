package com.example.cryptoinfoapp.presentation

sealed class Screen (val route: String) {
    object CoinListcreen: Screen("coin_list_screen")
    object CoinDetailScreen: Screen("coin_detail_screen")
}