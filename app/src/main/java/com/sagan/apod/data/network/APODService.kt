package com.sagan.apod.data.network

import android.util.Log
import com.sagan.apod.Constants.APOD_API_KEY
import com.sagan.apod.data.model.APODResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class APODService @Inject constructor(
    private val api: APIService
  ){

    suspend fun getAPODsByCount(query: String): List<APODResponse?>{

        return withContext(Dispatchers.IO){
            val response = api.getAPODByCount("apod?api_key=${APOD_API_KEY}&count=$query")
            response.body() ?: emptyList()
        }
    }

    suspend fun getAPODsByDate(date: String): APODResponse?{

        return withContext(Dispatchers.IO){
            val response = api.getAPODByDate("apod?api_key=$APOD_API_KEY&date=$date&concept_tags=True")
            response.body()
        }
    }

    suspend fun getAPODsLastMonth(startDate: String, endDate: String): List <APODResponse?>{

        return withContext(Dispatchers.IO){
            val response = api.getAPODLastMonth("apod?api_key=$APOD_API_KEY&start_date=$startDate&end_date=$endDate")
            Log.i("getAPODsLastMonth",response.toString())
            response.body() ?: emptyList()
        }
    }
}