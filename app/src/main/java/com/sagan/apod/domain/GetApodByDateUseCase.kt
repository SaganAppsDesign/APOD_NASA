package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.databse.entities.toDataBase
import com.sagan.apod.data.model.APODModel
import com.sagan.apod.domain.model.APOD
import javax.inject.Inject

class GetApodByDateUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (date: String) : APOD = repository.getAPODByDateFromApi(date)

//    suspend operator fun invoke (date: String) : APODModel? = repository.getAPODByDateFromApi(date)
//    suspend operator fun invoke (date: String) : APOD {
//    return if(repository.EmptyDataBase()){
//        val apod = repository.getAPODByDateFromApi(date)
//        repository.insertAPODByDateFromDataBase(apod.toDataBase())
//        apod
//    } else {
//        repository.getAPODByDateDataBase()
//    }
//    }

}