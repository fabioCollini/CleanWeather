package it.codingjam.cleanweather.domain.coroutinePost2

import kotlinx.coroutines.*

private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    throwable.printStackTrace()
}

private val viewModelScope = CoroutineScope(Dispatchers.IO + Job() + exceptionHandler)

fun main() = runBlocking {
    suspend fun doSomething() = coroutineScope {
        val deferred1 = async(Dispatchers.IO) {
            println("Start 1...")
            delay(1000)
            println("After delay 1")
        }
        val deferred2 = async(Dispatchers.IO) {
            println("Start 2...")
            delay(2000)
            println(1 / 0)
            println("After delay 2")
        }
        val deferred3 = async(Dispatchers.IO) {
            println("Start 3...")
            delay(3000)
            println("After delay 3")
        }
        deferred1.await()
        deferred2.await()
        deferred3.await()
    }

    viewModelScope.launch {
        try {
            coroutineScope {
                val deferred1 = async(Dispatchers.IO) {
                    println("Start 1...")
                    delay(1000)
                    println("After delay 1")
                }
                val deferred2 = async(Dispatchers.IO) {
                    println("Start 2...")
                    delay(2000)
                    println(1 / 0)
                    println("After delay 2")
                }
                val deferred3 = async(Dispatchers.IO) {
                    println("Start 3...")
                    delay(3000)
                    println("After delay 3")
                }
                deferred1.await()
                deferred2.await()
                deferred3.await()
            }
        } catch (e: Exception) {
            println("Managing the error `${e.message}`...")
        }
    }
            .join()
}

