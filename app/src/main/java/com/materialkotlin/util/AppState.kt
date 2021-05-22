package com.materialkotlin.util

import com.materialkotlin.data.remote.NasaResponse

sealed class AppState {
    data class Success(val data: NasaResponse) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}