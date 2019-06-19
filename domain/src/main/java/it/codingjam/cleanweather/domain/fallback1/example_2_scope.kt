package it.codingjam.cleanweather.domain.fallback1

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

suspend fun loadDataSequential(): Int =
    try {
      callServer() + loadPrefs()
    } catch (e: Exception) {
      loadFallback()
    }

suspend fun loadDataExternal(): Int = coroutineScope {
  try {
    val deferred = async { loadPrefs() }
    callServer() + deferred.await()
  } catch (e: Exception) {
    loadFallback()
  }
}

suspend fun loadDataSupervisor(): Int = supervisorScope {
  try {
    val deferred = async { loadPrefs() }
    callServer() + deferred.await()
  } catch (e: Exception) {
    loadFallback()
  }
}

suspend fun loadData(): Int =
    try {
      coroutineScope {
        val deferred = async { loadPrefs() }
        callServer() + deferred.await()
      }
    } catch (e: Exception) {
      loadFallback()
    }

fun initData() = viewModelScope.launch {
  val d = callServer() + loadPrefs()
  updateUi(d)
}

fun main() = runBlocking {
  initData().join()
}