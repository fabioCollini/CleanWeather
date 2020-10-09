package it.codingjam.cleanweather.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    inline fun <reified T> createService(
        client: OkHttpClient,
        baseUrl: String = "https://api.openweathermap.org/data/2.5/"
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .client(client)
        .build()
        .create(T::class.java)
}