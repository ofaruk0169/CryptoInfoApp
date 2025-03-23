package com.example.cryptoinfoapp.end_to_end

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.espresso.Espresso
import com.example.cryptoinfoapp.di.AppModule
import com.example.cryptoinfoapp.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
@UninstallModules(AppModule::class)
class CoinAppEndToEndTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun complete_app_navigation_flow() {
        // Step 1: Verify the list screen loads with coin data
        // Wait for the UI to be ready
        composeRule.waitForIdle()

        // Check that Bitcoin is displayed with correct information
        val bitcoinItem = composeRule.onNodeWithText("1. Bitcoin (BTC)", substring = true)
        bitcoinItem.assertExists()
        bitcoinItem.assertIsDisplayed()

        // Check that Ethereum is also displayed (verifying multiple items)
        composeRule.onNodeWithText("2. Ethereum (ETH)", substring = true)
            .assertExists()
            .assertIsDisplayed()

        // Step 2: Navigate to Bitcoin detail screen
        bitcoinItem.performClick()

        // Wait for navigation to complete
        composeRule.waitForIdle()

        // Step 3: Verify the detail screen content
        // Check the title is displayed
        composeRule.onNodeWithText("1. Bitcoin (BTC)", substring = true)
            .assertExists()
            .assertIsDisplayed()

        // Check the description is displayed
        composeRule.onNodeWithText("Bitcoin is a cryptocurrency", substring = true)
            .assertExists()
            .assertIsDisplayed()

        // Check for section headers
        composeRule.onNodeWithText("Tags")
            .assertExists()
            .assertIsDisplayed()

        composeRule.onNodeWithText("Team Members")
            .assertExists()
            .assertIsDisplayed()

        // Check for specific tag content
        composeRule.onNodeWithText("Cryptocurrency", substring = true)
            .assertExists()
            .assertIsDisplayed()

        // Check for team member
        composeRule.onNodeWithText("Satoshi Nakamoto")
            .assertExists()
            .assertIsDisplayed()

        // Step 4: Navigate back to the list screen using Espresso's back press
        // This uses the correct thread handling
        Espresso.pressBack()

        // Wait for navigation back to complete
        composeRule.waitForIdle()

        // Step 5: Verify we returned to the list screen
        // Check that Bitcoin is displayed again
        composeRule.onNodeWithText("1. Bitcoin (BTC)", substring = true)
            .assertExists()
            .assertIsDisplayed()

        // Check that Ethereum is displayed again
        composeRule.onNodeWithText("2. Ethereum (ETH)", substring = true)
            .assertExists()
            .assertIsDisplayed()
    }
}