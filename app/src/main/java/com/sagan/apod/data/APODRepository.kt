package com.sagan.apod.data

import androidx.activity.ComponentActivity
import com.sagan.apod.data.databse.dao.ApodDao
import com.sagan.apod.data.databse.entities.ApodEntity
import com.sagan.apod.data.model.APODModel
import com.sagan.apod.data.network.APODService
import com.sagan.apod.domain.model.APOD
import com.sagan.apod.domain.model.toDomain
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class APODRepository @Inject constructor(
    private val api : APODService,
    private val apodDAO: ApodDao
    ): ComponentActivity() {

    suspend fun getAPODsByCountFromApi(query: String): List<APODModel?> {
        return api.getAPODsByCount(query)
    }


    suspend fun getAPODByDateFromApi(date: String): APOD {
        val response =  api.getAPODsByDate(date)
        return response?.toDomain() ?: APOD("")
    }

//    suspend fun getAPODByDateDataBase(): APOD{
//        val response = apodDAO.getCurrentApod()
//        return response.toDomain()
//    }
//
//    suspend fun insertAPODByDateFromDataBase(apod: ApodEntity){
//        apodDAO.insertCurrentApod(apod)
//    }

    suspend fun getAPODLast30FromApi(startDate: String, endDate: String): List <APOD?>{
        val response = api.getAPODsLast30(startDate, endDate)
        return response.map {it?.toDomain()}
    }

    suspend fun getAPODLast30FromDataBase(): List <APOD?>{
        val response = apodDAO.getAll()
        return response.map { it.toDomain() }
    }

    suspend fun insertAPODLast30FromDataBase(apods: List<ApodEntity>){
        apodDAO.insertAll(apods)
    }

    suspend fun deleteTable(){
        apodDAO.delete()
    }
    suspend fun EmptyDataBase(): Boolean{
        return apodDAO.isEmpty()
    }







}

