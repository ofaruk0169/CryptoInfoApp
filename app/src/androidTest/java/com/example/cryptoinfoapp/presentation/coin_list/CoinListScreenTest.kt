package com.example.cryptoinfoapp.presentation.coin_list

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.cryptoinfoapp.di.AppModule
import com.example.cryptoinfoapp.presentation.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@UninstallModules(AppModule::class)
class CoinListScreenTest {

    // Hilt Rule to ensure dependencies are injected before each test
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()
        composeTestRule.setContent {

        }
    }

}