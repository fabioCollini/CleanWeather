package it.codingjam.cleanweather.domain.coroutinePost5

import kotlinx.coroutines.*
import kotlin.random.Random

val start = System.currentTimeMillis()

fun log(s: String?) = println("[${System.currentTimeMillis() - start}] $s")

val exceptionHandler = CoroutineExceptionHandler { _, t ->
  t.printStackTrace()
}

val viewModelScope = CoroutineScope(Dispatchers.IO + SupervisorJob() + exceptionHandler)

fun showError(e: Exception) = log(e.message)

fun updateUi(data: Int) = log("New value: $data")

suspend fun loadPrefs(): Int = withContext(Dispatchers.IO) {
  delay(300)
  val v = 10000 / Random.nextInt(1000)
  log("prefs: $v")
  v
}

suspend fun callServer(): Int {
  delay(500)
  val v = 1 / Random.nextInt(2)
  log("server: $v")
  return v
}

suspend fun loadFallback(): Int {
  delay(700)
  val v = 100 / Random.nextInt(10)
  log("fallback: $v")
  return v
}

suspend fun loadData(): Int =
    try {
      coroutineScope {
        val deferred = async { callServer() }
        loadPrefs() + deferred.await()
      }
    } catch (e: Exception) {
      loadFallback()
    }

fun initData() = viewModelScope.launch {
  try {
    val d = loadData()
    updateUi(d)
  } catch (e: Exception) {
    showError(e)
  }
}

fun main() = runBlocking {
  initData().join()
}