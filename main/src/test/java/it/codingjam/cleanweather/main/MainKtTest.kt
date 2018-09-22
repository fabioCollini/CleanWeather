package it.codingjam.cleanweather.main

import kotlinx.coroutines.runBlocking
import org.junit.Test


class MainKtTest {
    @Test
    fun runMain() = runBlocking {
        main(arrayOf("Springfield"))
    }
}