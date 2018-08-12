package it.codingjam.cleanweather.api

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import okio.Okio
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.nio.charset.StandardCharsets

class MockWebServerRule : TestWatcher() {
    private val mockWebServer = MockWebServer()

    override fun starting(description: Description?) {
    }

    override fun finished(description: Description?) {
        mockWebServer.shutdown()
    }

    fun takeRequest(): RecordedRequest = mockWebServer.takeRequest()

    val url get() = mockWebServer.url("/").toString()

    fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader.getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        headers.forEach { key, value -> mockResponse.addHeader(key, value) }
        mockWebServer.enqueue(mockResponse
                .setBody(source.readString(StandardCharsets.UTF_8)))
    }
}