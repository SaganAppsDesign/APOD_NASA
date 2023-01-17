package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.databse.entities.toDataBase
import com.sagan.apod.data.model.APODModel
import com.sagan.apod.domain.model.APOD
import javax.inject.Inject

class GetApodByDateUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (date: String) : APOD = repository.getAPODByDateFromApi(date)
}