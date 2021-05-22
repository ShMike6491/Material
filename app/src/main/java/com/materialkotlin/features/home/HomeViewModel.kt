package com.materialkotlin.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.materialkotlin.data.remote.NasaResponse
import com.materialkotlin.data.remote.NasaService
import com.materialkotlin.data.repositories.Repository
import com.materialkotlin.util.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = Repository(NasaService.create())
) : ViewModel() {
    fun getLiveData() : LiveData<AppState> = liveData

    fun requestDailyImage() {
        liveData.value = AppState.Loading
        repository.getDailyImage(callback)
    }

    private val callback = object : Callback<NasaResponse> {
        override fun onResponse(call: Call<NasaResponse>, response: Response<NasaResponse>) {
            val res: NasaResponse? = response.body()
            liveData.postValue(
                if(response.isSuccessful && res != null) {
                    AppState.Success(res)
                } else {
                    AppState.Error(Throwable("response error"))
                }
            )
        }

        override fun onFailure(call: Call<NasaResponse>, t: Throwable) {
            liveData.postValue(AppState.Error(Throwable(t.message ?: "request error")))
        }
    }
}