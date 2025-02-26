package com.example.cryptoinfoapp.presentation.coin_list

import android.util.Log
import com.example.cryptoinfoapp.data.remote.dto.CoinDetailDto
import com.example.cryptoinfoapp.data.remote.dto.CoinDto
import com.example.cryptoinfoapp.data.remote.dto.Links
import com.example.cryptoinfoapp.data.remote.dto.LinksExtended
import com.example.cryptoinfoapp.data.remote.dto.Stats
import com.example.cryptoinfoapp.data.remote.dto.Tag
import com.example.cryptoinfoapp.data.remote.dto.TeamMember
import com.example.cryptoinfoapp.data.remote.dto.Whitepaper
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import javax.inject.Inject


/**
 * A fake repository that returns hardcoded data for testing.
 */
class FakeCoinRepository @Inject constructor() : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        Log.d("FakeRepo", "Returning fake coin list data")
        return listOf(
            CoinDto(
                id = "btc-bitcoin",
                name = "Bitcoin",
                symbol = "BTC",
                rank = 1,
                isActive = true,
                isNew = false,
                type = "coin"
            ),
            CoinDto(
                id = "eth-ethereum",
                name = "Ethereum",
                symbol = "ETH",
                rank = 2,
                isActive = true,
                isNew = false,
                type = "coin"
            ),
            CoinDto(
                id = "usdt-tether",
                name = "Tether",
                symbol = "USDT",
                rank = 3,
                isActive = true,
                isNew = false,
                type = "token"
            )
        )
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        Log.d("FakeRepo", "Returning fake coin detail data for $coinId")
        return CoinDetailDto(
            id = "btc-bitcoin",
            name = "Bitcoin",
            description = "Bitcoin is a cryptocurrency and worldwide payment system.",
            symbol = "BTC",
            rank = 1,
            isActive = true,
            isNew = false,
            type = "coin",
            logo = "https://static.coinpaprika.com/coin/btc-bitcoin/logo.png",
            tags = listOf(
                Tag(
                    id = "segwit",
                    name = "Segwit",
                    coin_counter = 15,
                    ico_counter = 0
                ),
                Tag(
                    id = "cryptocurrency",
                    name = "Cryptocurrency",
                    coin_counter = 1037,
                    ico_counter = 40
                )
            ),
            team = listOf(
                TeamMember(
                    id = "satoshi-nakamoto",
                    name = "Satoshi Nakamoto",
                    position = "Founder"
                )
            ),
            links = Links(
                explorer = listOf("https://blockchain.info/"),
                facebook = listOf("https://facebook.com/bitcoin"),
                reddit = listOf("https://reddit.com/r/bitcoin"),
                source_code = listOf("https://github.com/bitcoin/bitcoin"),
                website = listOf("https://bitcoin.org/"),
                youtube = listOf("https://youtube.com/bitcoin")
            ),
            linksExtended = listOf(
                LinksExtended(
                    url = "https://github.com/bitcoin/bitcoin",
                    type = "source_code",
                    stats = Stats(
                        subscribers = 0,
                        contributors = 0,
                        stars = 0,
                        followers = 0
                    )
                )
            ),
            whitepaper = Whitepaper(
                link = "https://bitcoin.org/bitcoin.pdf",
                thumbnail = "https://static.coinpaprika.com/coin/btc-bitcoin/whitepaper.png"
            ),
            firstDataAt = "2010-07-17T00:00:00Z",
            lastDataAt = "2023-03-30T00:00:00Z",
            developmentStatus = "Working product",
            hardwareWallet = true,
            hashAlgorithm = "SHA256",
            message = "",
            openSource = true,
            orgStructure = "Decentralized",
            proofType = "Proof of Work",
            startedAt = "2009-01-03T00:00:00Z"
        )
    }
}