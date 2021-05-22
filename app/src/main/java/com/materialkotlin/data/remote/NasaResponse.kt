package com.materialkotlin.data.remote

import com.google.gson.annotations.SerializedName

data class NasaResponse(
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("explanation") val description: String?,
    @field:SerializedName("media_type") val media: String?,
    @field:SerializedName("title") val title: String?,
    @field:SerializedName("url") val url: String?
)