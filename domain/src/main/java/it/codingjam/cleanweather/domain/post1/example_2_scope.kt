package it.codingjam.cleanweather.domain.post1

import kotlinx.coroutines.*

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
  val v = 10000 / 0 //Random.nextInt(1000)
  log("prefs: $v")
  v
}

suspend fun callServer(): Int {
  delay(1000)
  val v = 1 / 1 //Random.nextInt(2)
  log("server: $v")
  return v
}

fun initData() = viewModelScope.launch {
  try {
    coroutineScope {
      val deferred2 = async { loadPrefs() }
      val d = callServer() + deferred2.await()
      updateUi(d)
    }
  } catch (e: Exception) {
    showError(e)
  }
}

fun main() = runBlocking {
  initData().join()
}