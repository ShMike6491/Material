package com.materialkotlin.data.repositories

import com.materialkotlin.data.remote.NasaResponse
import com.materialkotlin.data.remote.NasaService
import retrofit2.Callback

class Repository (private val service: NasaService) {
    fun getDailyImage(callback: Callback<NasaResponse>) {
        val apiKey = NasaService.API_KEY
        service.getDailyPicture(apiKey).enqueue(callback)
    }
}