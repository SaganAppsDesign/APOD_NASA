package com.sagan.apod.data.network

import com.sagan.apod.data.model.APODModel
import com.sagan.apod.domain.model.APOD
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApodAPIClient {

    @GET
    suspend fun getAPODByDate(@Url url:String): Response<APODModel>

    @GET
    suspend fun getAPODByCount(@Url url:String): Response<List<APODModel>>

    @GET
    suspend fun getAPODLast30(@Url url:String): Response<List<APODModel>>

}