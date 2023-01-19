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
        return response?.toDomain() ?: APOD("","$date","","","image","No image available fot that date","https://firebasestorage.googleapis.com/v0/b/cumplesdepablo.appspot.com/o/no_image.png?alt=media&token=48335ffd-18bd-4831-b37b-136d3cbf8829","")
    }

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
    suspend fun emptyDataBase(): Boolean{
        return apodDAO.isEmpty()
    }
}

