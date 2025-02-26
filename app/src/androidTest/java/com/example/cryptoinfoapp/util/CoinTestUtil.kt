package com.example.cryptoinfoapp.util

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

object CoinTestUtil {

    /**
     * Creates a mock response from a JSON file in the resources directory
     */
    fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
        val inputStream = javaClass.classLoader?.getResourceAsStream("api-responses/$fileName")

        val source = inputStream?.source()?.buffer()
        source?.let {
            enqueue(
                MockResponse()
                    .setResponseCode(code)
                    .setBody(source.readString(StandardCharsets.UTF_8))
            )
        }
    }

    /**
     * Returns a mock response directly from a string
     */
    fun createMockResponse(body: String, responseCode: Int): MockResponse {
        return MockResponse()
            .setResponseCode(responseCode)
            .setBody(body)
    }
}