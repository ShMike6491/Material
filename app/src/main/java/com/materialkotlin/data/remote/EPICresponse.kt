package com.materialkotlin.data.remote

import com.google.gson.annotations.SerializedName

data class EPICresponse (
    @field:SerializedName("identifier") val media: String?,
    @field:SerializedName("date") val date: String?,
    @field:SerializedName("image") val image: String?,
    @field:SerializedName("caption") val caption: String?
)