package com.sagan.apod.data

import com.sagan.apod.data.model.APODResponse
import com.sagan.apod.data.network.APODService
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

