package com.example.cryptoinfoapp.presentation.coin_list

import android.os.StrictMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptoinfoapp.di.AppModule
import com.example.cryptoinfoapp.presentation.MainActivity
import com.example.cryptoinfoapp.presentation.Screen
import com.example.cryptoinfoapp.presentation.theme.CryptoInfoAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class CoinListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() = runBlocking {
        hiltRule.inject()  // Inject Hilt dependencies

        // Initialize MockWebServer
        mockWebServer = MockWebServer()

        // Start the MockWebServer on a background thread using runBlocking
        mockWebServer.start()

        // Enqueue a mock response for the API call
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
            [
                {
                    "id": "1",
                    "name": "Bitcoin",
                    "symbol": "BTC",
                    "rank": 1
                },
                {
                    "id": "2",
                    "name": "Ethereum",
                    "symbol": "ETH",
                    "rank": 2
                }
            ]
        """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // Use MockWebServer's URL for testing instead of the real API URL
        val mockWebServerUrl = mockWebServer.url("/")

        // Set content for the test
        composeTestRule.setContent {
            val navController = rememberNavController()

            CryptoInfoAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = Screen.CoinListScreen.route
                ) {
                    composable(route = Screen.CoinListScreen.route) {
                        CoinListScreen(navController = navController)
                    }
                }
            }
        }
    }

    @After
    fun tearDown() = runBlocking {
        // Shut down the MockWebServer after the tests
        mockWebServer.shutdown()
    }

    @Test
    fun testCoinListScreen_DisplayCoins() {
        // Perform your UI tests here
        composeTestRule.onNodeWithText("Bitcoin").assertExists()
        composeTestRule.onNodeWithText("Ethereum").assertExists()
    }
}
