package com.example.apod.data.network

import com.example.apod.Constants
import com.example.apod.core.RetrofitHelper
import com.example.apod.data.model.APODResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class APODService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getAPODSByCount(query: String): List<APODResponse?>{

        return withContext(Dispatchers.IO){
            val response = retrofit.create(APIService::class.java).getAPODByCount("apod?api_key=${Constants.APOD_API_KEY}&count=$query")
            response.body() ?: emptyList()
        }
    }
}