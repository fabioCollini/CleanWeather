package it.codingjam.cleanweather.domain.ex6_inner_coroutine_scope

import kotlinx.coroutines.*
import kotlin.random.Random

val start = System.currentTimeMillis()

fun log(s: String?) = println("[${System.currentTimeMillis() - it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.start}] $s")

val exceptionHandler = CoroutineExceptionHandler { _, t ->
  t.printStackTrace()
}

val viewModelScope = CoroutineScope(Dispatchers.IO + Job() + it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.exceptionHandler)

fun updateUi(data: Int) = it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.log("New value: $data")

private suspend fun load(s: String, time: Long, fail: Boolean): Int {
  if (fail) {
      it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.log("$s error")
    throw Exception("$s error!")
  }
  val v = Random.nextInt(50)
  delay(time)
    it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.log("$s: $v")
  return v
}

suspend fun callServer(fail: Boolean = false) = load("server", 1000, fail)

suspend fun loadPrefs(fail: Boolean = false) = load("prefs", 500, fail)

suspend fun loadFallback() = load("fallback", 100, false)

suspend fun loadData(): Int =
    try {
      coroutineScope {
        val deferred = async { it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.callServer() }
        val prefsValue = it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.loadPrefs()
        val serverValue = deferred.await()
        prefsValue + serverValue
      }
    } catch (e: Exception) {
        it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.loadFallback()
    }

fun initData() = it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.viewModelScope.launch {
  val value = it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.loadData()
    it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.updateUi(value)
}

fun main() = runBlocking {
  it.codingjam.cleanweather.domain.finalExample.ex6_inner_coroutine_scope.initData().join()
}