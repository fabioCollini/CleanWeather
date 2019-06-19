package it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope

import kotlinx.coroutines.*
import kotlin.random.Random

val start = System.currentTimeMillis()

fun log(s: String?) = println("[${System.currentTimeMillis() - start}] $s")

val exceptionHandler = CoroutineExceptionHandler { _, t ->
  t.printStackTrace()
}

val viewModelScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)

fun updateUi(data: Int) = log("New value: $data")

private suspend fun load(s: String, time: Long, fail: Boolean): Int {
  if (fail) {
    log("$s error")
    throw Exception("$s error!")
  }
  val v = Random.nextInt(50)
  delay(time)
  log("$s: $v")
  return v
}

suspend fun callServer(fail: Boolean = false) = load("server", 1000, fail)

suspend fun loadPrefs(fail: Boolean = false) = load("prefs", 500, fail)

suspend fun loadFallback() = load("fallback", 100, false)

suspend fun loadData(): Int =
    try {
      coroutineScope {
        val deferred = async { callServer() }
        val prefsValue = loadPrefs()
        val serverValue = deferred.await()
        prefsValue + serverValue
      }
    } catch (e: Exception) {
      loadFallback()
    }

fun initData() = viewModelScope.launch {
  val value = loadData()
  updateUi(value)
}

fun main() = runBlocking {
  initData().join()
}