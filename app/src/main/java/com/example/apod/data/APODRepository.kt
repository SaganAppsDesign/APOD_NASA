package com.example.apod.data

import com.example.apod.data.model.APODResponse
import com.example.apod.data.network.APODService
import javax.inject.Inject

class APODRepository @Inject constructor(private val api : APODService){

    suspend fun getAPODsByCount(query: String): List<APODResponse?> {
        return api.getAPODSByCount(query)
    }

    suspend fun getAPODByDate(date: String):APODResponse?{
        return api.getAPODSByDate(date)
    }
}

