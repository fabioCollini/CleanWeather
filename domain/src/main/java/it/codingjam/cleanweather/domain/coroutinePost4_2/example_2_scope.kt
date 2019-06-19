package it.codingjam.cleanweather.domain.coroutinePost4_2

import kotlinx.coroutines.*

val start = System.currentTimeMillis()

fun log(s: String?) = println("[${System.currentTimeMillis() - it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.start}] $s")

val exceptionHandler = CoroutineExceptionHandler { _, t ->
  t.printStackTrace()
}

val viewModelScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.exceptionHandler)

fun showError(e: Exception) = it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.log(e.message)

fun updateUi(data: Int) = it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.log("New value: $data")

suspend fun loadPrefs(): Int = withContext(Dispatchers.IO) {
  delay(300)
  val v = 10000 / 0 //Random.nextInt(1000)
    it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.log("prefs: $v")
  v
}

suspend fun callServer(): Int {
  delay(500)
  val v = 1 // Random.nextInt(2)
    it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.log("server: $v")
  return v
}

suspend fun loadFallback(): Int {
  delay(700)
  val v = 100 // Random.nextInt(10)
    it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.log("fallback: $v")
  return v
}

fun initData() = it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.viewModelScope.launch {
//  try {
    val d = supervisorScope {
      try {
        val deferred = async { it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.loadPrefs() }
        it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.callServer() + deferred.await()
      } catch (e: Exception) {
        loadFallback()
      }
    }
    it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.updateUi(d)
//  } catch (e: Exception) {
//    showError(e)
//  }
}

fun main() = runBlocking {
  it.codingjam.cleanweather.domain.finalExample.ex1_synchronous.initData().join()
}