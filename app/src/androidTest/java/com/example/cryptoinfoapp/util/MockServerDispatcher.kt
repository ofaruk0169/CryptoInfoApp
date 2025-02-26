package com.example.cryptoinfoapp.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockServerDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            "/v1/coins" -> {
                MockResponse()
                    .setResponseCode(200)
                    .setBody(getJsonContent("fake_coins_response.json"))
            }
            else -> MockResponse().setResponseCode(404)
        }
    }

    private fun getJsonContent(fileName: String): String {
        val json = javaClass.classLoader?.getResource(fileName)?.readText()
            ?: throw IllegalArgumentException("JSON file not found: $fileName")
        return json
    }
}