package com.example.apod.domain

import com.example.apod.data.APODRepository
import com.example.apod.data.model.APODResponse
import javax.inject.Inject

class GetLast50ApodsUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (startDate: String, endDate: String) : List <APODResponse?> = repository.getAPODLastMonth(startDate, endDate)
}