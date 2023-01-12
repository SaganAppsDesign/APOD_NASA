package com.nasa.apod.data

import com.nasa.apod.data.model.APODResponse
import com.nasa.apod.data.network.APODService
import javax.inject.Inject

class APODRepository @Inject constructor(private val api : APODService){

    suspend fun getAPODsByCount(query: String): List<APODResponse?> {
        return api.getAPODsByCount(query)
    }

    suspend fun getAPODByDate(date: String):APODResponse?{
        return api.getAPODsByDate(date)
    }

    suspend fun getAPODLastMonth(startDate: String, endDate: String): List <APODResponse?>{
        return api.getAPODsLastMonth(startDate, endDate)
    }
}

