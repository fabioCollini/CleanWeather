package it.codingjam.cleanweather.domain.coroutinePost3

import kotlinx.coroutines.*

private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
    println("Error in ${context[CoroutineName]}")
    throwable.printStackTrace()
}

private val viewModelScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)

fun main() = runBlocking {
    viewModelScope.launch(CoroutineName("topLevelLaunch")) {
        supervisorScope {
            async(Dispatchers.IO + CoroutineName("async1")) {
                println("Start 1...")
                delay(1000)
                println(1 / 0)
                println("After delay 1")
            }
            async(Dispatchers.IO + CoroutineName("async2")) {
                println("Start 2...")
                delay(2500)
                println("After delay 2")
            }
        }
    }
            .join()
}
