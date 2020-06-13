package it.codingjam.cleanweather.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    inline fun <reified T> createService(
            baseUrl: String = "https://api.openweathermap.org/data/2.5/",
            debug: Boolean = false): T {
        val httpClient = OkHttpClient.Builder()

        if (debug) {
            val logging = HttpLoggingInterceptor().apply { level = BODY }
            httpClient.addInterceptor(logging)
        }

        val gson = GsonBuilder().create()

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build()
                .create(T::class.java)
    }
}