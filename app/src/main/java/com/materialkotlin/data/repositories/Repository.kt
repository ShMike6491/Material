package com.materialkotlin.data.repositories

import android.util.Log
import com.materialkotlin.data.remote.EPICresponse
import com.materialkotlin.data.remote.NasaResponse
import com.materialkotlin.data.remote.NasaService
import java.io.IOException

private const val DEFAULT_IMAGE_URL = "https://epic.gsfc.nasa.gov/archive/natural/2021/06/02/jpg/epic_1b_20210602033414.jpg"

class Repository (private val service: NasaService) {
    suspend fun getDailyImage(): NasaResponse? {
        return try {
            service.getDailyPicture(NasaService.API_KEY)
        } catch (error: IOException) {
            Log.e("Request error", error.message, error)
            null
        }
    }

    suspend fun getNaturalImage(): String {
        return try {
            val response = service.getNaturalImage(NasaService.API_KEY)
            convertToImageUrl(response[0])
        } catch (error: IOException) {
            Log.e("Request error", error.message, error)
            DEFAULT_IMAGE_URL
        }
    }

    private fun convertToImageUrl(data: EPICresponse) : String {
        var date = data.date
        val url = data.image
        if ( date.isNullOrBlank() && url.isNullOrBlank() ) { return DEFAULT_IMAGE_URL }
        date = date!!.split(" ")[0].replace('-', '/')
        return "https://epic.gsfc.nasa.gov/archive/natural/${date}/jpg/${url}.jpg"
    }
}