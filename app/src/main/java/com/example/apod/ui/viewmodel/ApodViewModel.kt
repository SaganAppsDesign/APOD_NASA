package com.example.apod.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.data.model.APODResponse
import com.example.apod.domain.GetApodByCountUseCase
import kotlinx.coroutines.launch

class ApodViewModel : ViewModel() {

    val apodLiveData = MutableLiveData <MutableList <APODResponse?>>()
    val isLoading = MutableLiveData <Boolean>()

    @SuppressLint("StaticFieldLeak")
    val getApodByCountUseCase = GetApodByCountUseCase()

    fun getApodData(query: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val apodData:List <APODResponse?> = getApodByCountUseCase(query)
            if(apodData.isNotEmpty()){
                apodLiveData.postValue(apodData as MutableList<APODResponse?>?)
                isLoading.postValue(false)
            }
        }
     }
}