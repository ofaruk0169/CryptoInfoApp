package com.example.cryptoinfoapp.domain.use_case.get_coins

import com.example.cryptoinfoapp.common.Resource
import com.example.cryptoinfoapp.data.remote.dto.CoinDto
import com.example.cryptoinfoapp.domain.repository.CoinRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetCoinsUseCaseTest {

    private lateinit var getCoinsUseCase: GetCoinsUseCase
    private lateinit var repository: CoinRepository

    @Before
    fun setUp() {
        repository = mockk() // Mock the repository
        getCoinsUseCase = GetCoinsUseCase(repository)
    }

    @Test
    fun `invoke returns list of coins successfully`() = runTest {
        // Arrange - Define mock response
        val mockCoinDtos = listOf(
            CoinDto(id = "bitcoin", isActive = true, isNew = false, name = "Bitcoin", rank = 1, symbol = "BTC", type = "coin"),
            CoinDto(id = "ethereum", isActive = true, isNew = false, name = "Ethereum", rank = 2, symbol = "ETH", type = "coin")
        )

        coEvery { repository.getCoins() } returns mockCoinDtos

        // Act - Collect all emitted values
        val results = getCoinsUseCase().toList()  // Collects all emissions in a list

        // Assert - Ensure correct flow emission order
        assert(results.first() is Resource.Loading)  // First should be loading
        assert(results.last() is Resource.Success)  // Last should be success

        val successData = (results.last() as Resource.Success).data
        assert(successData?.size == mockCoinDtos.size)
        assert(successData?.first()?.id == "bitcoin")
    }
}