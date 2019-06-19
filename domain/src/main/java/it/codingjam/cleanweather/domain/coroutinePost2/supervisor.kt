package it.codingjam.cleanweather.domain.coroutinePost2

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    launch(handler) {
        //        coroutineScope {
        launch {
            delay(1000)
            println("Child throws an exception")
            throw AssertionError()
        }
        launch {
            delay(2000)
            println("end")
        }
//        }
    }.join()
    println("Scope is completed")
}