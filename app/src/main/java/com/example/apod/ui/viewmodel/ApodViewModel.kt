package com.example.apod.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apod.data.model.APODResponse
import com.example.apod.data.network.GetApodByCount.searchByCount
import com.example.apod.ui.view.recyclerview.APODAdapter

class ApodViewModel : ViewModel() {

    val apodLiveData = MutableLiveData <MutableList <APODResponse?>>()

    fun getApodData(query: String, adapter: APODAdapter){

        val apodData: MutableList <APODResponse?> = searchByCount(query, adapter)
        apodLiveData.postValue(apodData)
    }
}