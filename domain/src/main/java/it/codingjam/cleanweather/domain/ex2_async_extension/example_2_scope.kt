package it.codingjam.cleanweather.domain.ex2_async_extension

import kotlinx.coroutines.*
import kotlin.random.Random

val start = System.currentTimeMillis()

fun log(s: String?) = println("[${System.currentTimeMillis() - start}] $s")

val exceptionHandler = CoroutineExceptionHandler { _, t ->
  t.printStackTrace()
}

val viewModelScope = CoroutineScope(Dispatchers.IO + Job() + exceptionHandler)

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

suspend fun CoroutineScope.loadData(): Int =
try {
  val deferred = async { callServer(true) }
  val prefsValue = loadPrefs()
  val serverValue = deferred.await()
  prefsValue + serverValue
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