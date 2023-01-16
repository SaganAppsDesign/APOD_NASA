package com.sagan.apod.ui.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sagan.apod.data.model.APODModel
import com.sagan.apod.domain.GetApodByCountUseCase
import com.sagan.apod.domain.GetApodByDateUseCase
import com.sagan.apod.domain.GetLast30ApodsUseCase
import com.sagan.apod.domain.model.APOD
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodByCountUseCase : GetApodByCountUseCase,
    private val getApodByDateUseCase : GetApodByDateUseCase,
    private val getApodLast50UseCase : GetLast30ApodsUseCase
): ViewModel() {

    val apodByCountLiveData = MutableLiveData <MutableList <APODModel?>>()
    val apodByDateLiveData = MutableLiveData <APODModel?>()
    val apodLast50LiveData = MutableLiveData <MutableList <APOD?>>()

    val isLoading = MutableLiveData <Boolean>()

    @SuppressLint("StaticFieldLeak")
    fun getApodByCount(query: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val apodData:List <APODModel?> = getApodByCountUseCase(query)
            if(apodData.isNotEmpty()){
                apodByCountLiveData.postValue(apodData as MutableList<APODModel?>?)
                isLoading.postValue(false)
            }
        }
     }

    fun getApodByDate(date: String, date2: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            var apodData: APODModel? = getApodByDateUseCase(date)
            if(apodData != null){
                apodByDateLiveData.postValue(apodData)
                isLoading.postValue(false)
            }
            else{
                apodData = getApodByDateUseCase(date2)
                apodByDateLiveData.postValue(apodData)
                isLoading.postValue(false)
            }
        }
    }

    fun getApodLast30(startDate: String, endDate: String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val apodData: List <APOD?> = getApodLast50UseCase(startDate, endDate)
            if(apodData.isNotEmpty()) {
                apodLast50LiveData.postValue(apodData as MutableList<APOD?>?)
            }
            isLoading.postValue(false)
        }
    }

}

