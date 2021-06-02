package com.materialkotlin.features.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.materialkotlin.data.remote.NasaResponse
import com.materialkotlin.data.remote.NasaService
import com.materialkotlin.data.repositories.Repository
import kotlinx.coroutines.launch

class HomeViewModel(
    private val liveData: MutableLiveData<NasaResponse> = MutableLiveData(),
    private val repository: Repository = Repository(NasaService.create())
) : ViewModel() {
    fun getLiveData() : LiveData<NasaResponse> = liveData

    fun requestDailyImage() {
        viewModelScope.launch {
            try {
                val response = repository.getDailyImage()
                liveData.postValue(response)
            } catch (error: Throwable) {
                Log.e("Request error", error.message, error)
            }
        }
    }
}