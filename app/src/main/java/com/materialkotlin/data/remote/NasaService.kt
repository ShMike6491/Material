package com.materialkotlin.data.remote

import com.materialkotlin.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NasaService {
    @GET("planetary/apod")
    fun getDailyPicture (@Query("api_key") apiKey: String) :
            Call<NasaResponse>

    companion object {
        private const val BASE_URL = "https://api.nasa.gov/"
        const val API_KEY = BuildConfig.NASA_API_KEY

        fun create(): NasaService {
            val logger = HttpLoggingInterceptor().apply { level = Level.BASIC }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NasaService::class.java)
        }
    }
}