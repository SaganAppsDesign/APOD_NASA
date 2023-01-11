package com.example.apod.data.network

import com.example.apod.Constants
import com.example.apod.Constants.APOD_API_KEY
import com.example.apod.core.RetrofitHelper
import com.example.apod.data.model.APODResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class APODService @Inject constructor(
    private val api: APIService
  ){

    suspend fun getAPODSByCount(query: String): List<APODResponse?>{

        return withContext(Dispatchers.IO){
            val response = api.getAPODByCount("apod?api_key=${Constants.APOD_API_KEY}&count=$query")
            response.body() ?: emptyList()
        }
    }

    suspend fun getAPODSByDate(date: String): APODResponse?{

        return withContext(Dispatchers.IO){
            val response = api.getAPODByDate("apod?api_key=$APOD_API_KEY&date=$date&concept_tags=True")
            response.body()
        }
    }

}