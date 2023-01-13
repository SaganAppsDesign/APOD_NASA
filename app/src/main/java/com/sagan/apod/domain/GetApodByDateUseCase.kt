package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.model.APODResponse
import javax.inject.Inject

class GetApodByDateUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (date: String) : APODResponse? = repository.getAPODByDate(date)
}