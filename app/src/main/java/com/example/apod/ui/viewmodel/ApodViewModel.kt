package com.example.apod.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apod.data.model.APODResponse
import com.example.apod.domain.GetApodByCountUseCase
import com.example.apod.domain.GetApodByDateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodByCountUseCase : GetApodByCountUseCase,
    private val getApodByDateUseCase : GetApodByDateUseCase
): ViewModel() {

    val apodByCountLiveData = MutableLiveData <MutableList <APODResponse?>>()
    val apodByDateLiveData = MutableLiveData <APODResponse?>()
    val isLoading = MutableLiveData <Boolean>()

    @SuppressLint("StaticFieldLeak")

    fun getApodByCount(query: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val apodData:List <APODResponse?> = getApodByCountUseCase(query)
            if(apodData.isNotEmpty()){
                apodByCountLiveData.postValue(apodData as MutableList<APODResponse?>?)
                isLoading.postValue(false)
            }
        }
     }

    fun getApodByDate(date: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val apodData: APODResponse? = getApodByDateUseCase(date)
            if(apodData != null){
                apodByDateLiveData.postValue(apodData)
                isLoading.postValue(false)
            }
        }
    }




}

