package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.databse.entities.toDataBase
import com.sagan.apod.domain.model.APOD
import javax.inject.Inject

class GetLast30ApodsUseCase @Inject constructor(private val repository : APODRepository){

    suspend operator fun invoke (startDate: String, endDate: String) : List <APOD?> {
       return if(repository.emptyDataBase()){
            val apods = repository.getAPODLast30FromApi(startDate, endDate)
            repository.insertAPODLast30FromDataBase(apods.map{it!!.toDataBase()})
            apods
        } else {
            repository.getAPODLast30FromDataBase()
        }
    }
}