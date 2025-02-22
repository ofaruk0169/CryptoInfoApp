package com.example.cryptoinfoapp.presentation.coin_list

import com.example.cryptoinfoapp.common.Resource
import com.example.cryptoinfoapp.domain.model.Coin
import com.example.cryptoinfoapp.domain.use_case.get_coins.GetCoinsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


//integration test (no UI involved)
@ExperimentalCoroutinesApi
class CoinListViewModelTest {

    // Mock dependencies
    private val getCoinsUseCase: GetCoinsUseCase = mockk()

    // Use a TestCoroutineDispatcher for coroutine execution
    private val testDispatcher = StandardTestDispatcher()

    // ViewModel instance (lateinit so we can initialize in @Before)
    private lateinit var viewModel: CoinListViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher) // Set dispatcher for coroutines

        // Fake data: list of coins
        val mockCoins = listOf(
            Coin(id = "bitcoin", name = "Bitcoin", symbol = "BTC", rank = 1, isActive = true),
            Coin(id = "ethereum", name = "Ethereum", symbol = "ETH", rank = 2, isActive = true)
        )

        // Mock getCoinsUseCase to return a flow emitting mock data
        coEvery { getCoinsUseCase() } returns flow {
            emit(Resource.Success(mockCoins))
        }

        // Initialize ViewModel
        viewModel = CoinListViewModel(getCoinsUseCase)
    }

    @Test
    fun `getCoins updates state with list of coins`() = runTest {
        // Wait for coroutine execution
        advanceUntilIdle()

        // Assert that state contains the expected list of coins
        val state = viewModel.state.value
        assert(state.coins == listOf(
            Coin(id = "bitcoin", name = "Bitcoin", symbol = "BTC", rank = 1, isActive = true),
            Coin(id = "ethereum", name = "Ethereum", symbol = "ETH", rank = 2, isActive = true)
        ))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the dispatcher after test
    }
}