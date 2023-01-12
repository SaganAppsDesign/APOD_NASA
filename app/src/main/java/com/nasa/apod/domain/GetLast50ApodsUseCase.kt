package com.nasa.apod.domain

import com.nasa.apod.data.APODRepository
import com.nasa.apod.data.model.APODResponse
import javax.inject.Inject

class GetLast50ApodsUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (startDate: String, endDate: String) : List <APODResponse?> = repository.getAPODLastMonth(startDate, endDate)
}