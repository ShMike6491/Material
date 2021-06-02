package com.materialkotlin.data.repositories

import com.materialkotlin.data.remote.NasaService

class Repository (private val service: NasaService) {
    suspend fun getDailyImage() = service.getDailyPicture(NasaService.API_KEY)
}