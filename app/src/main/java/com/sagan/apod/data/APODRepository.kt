package com.sagan.apod.data

import com.sagan.apod.data.databse.dao.ApodDao
import com.sagan.apod.data.databse.entities.ApodEntity
import com.sagan.apod.data.model.APODModel
import com.sagan.apod.data.network.APODService
import com.sagan.apod.domain.model.APOD
import com.sagan.apod.domain.model.toDomain
import javax.inject.Inject

class APODRepository @Inject constructor(
    private val api : APODService,
    private val apodDAo: ApodDao
    ){

    suspend fun getAPODsByCountFromApi(query: String): List<APODModel?> {
        return api.getAPODsByCount(query)
    }

    suspend fun getAPODByDateFromApi(date: String): APODModel? {
        return api.getAPODsByDate(date)
    }

    suspend fun getAPODLast30FromApi(startDate: String, endDate: String): List <APOD?>{
        val response = api.getAPODsLast30(startDate, endDate)
        return response.map {it?.toDomain()}
    }

    suspend fun getAPODLast30FromDataBase(): List <APOD?>{
        val response = apodDAo.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun insertAPODLast30FromDataBase(apods: List<ApodEntity>){
        apodDAo.insertAll(apods)
    }

    suspend fun deleteAPODLast30FromDataBase(apods: List<ApodEntity>){
        apodDAo.delete(apods)
    }
}

