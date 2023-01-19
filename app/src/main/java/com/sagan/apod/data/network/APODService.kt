package com.sagan.apod.data.network

import android.util.Log
import com.sagan.apod.Constants.APOD_API_KEY
import com.sagan.apod.data.model.APODModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class APODService @Inject constructor(
    private val api: ApodAPIClient
  ){
    suspend fun getAPODsByCount(query: String): List<APODModel?>{
        return withContext(Dispatchers.IO){
            val response = api.getAPODByCount("apod?api_key=${APOD_API_KEY}&count=$query&thumbs=True")
            response.body() ?: emptyList()
        }
    }

    suspend fun getAPODsByDate(date: String): APODModel?{
        return withContext(Dispatchers.IO){
            val response = api.getAPODByDate("apod?api_key=$APOD_API_KEY&date=$date&concept_tags=True&thumbs=True")
            response.body()
        }
    }

    suspend fun getAPODsLast30(startDate: String, endDate: String): List <APODModel?>{
        return withContext(Dispatchers.IO){
            val response = api.getAPODLast30("apod?api_key=$APOD_API_KEY&start_date=$startDate&end_date=$endDate&thumbs=True")
            response.body() ?: emptyList()
        }
    }
}