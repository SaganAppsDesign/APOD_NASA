package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.model.APODResponse
import javax.inject.Inject

class GetLast50ApodsUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (startDate: String, endDate: String) : List <APODResponse?> = repository.getAPODLastMonth(startDate, endDate)
}