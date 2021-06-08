package com.materialkotlin.features.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.materialkotlin.data.remote.NasaService
import com.materialkotlin.data.repositories.Repository
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val repository: Repository = Repository(NasaService.create())
) : ViewModel() {
    private val _epicList: MutableLiveData<String> = MutableLiveData()
    val epicList: LiveData<String> get() {
        getEarthImage()
        return _epicList
    }

    fun getEarthImage() = viewModelScope.launch {
        _epicList.postValue(repository.getNaturalImage())
    }
}