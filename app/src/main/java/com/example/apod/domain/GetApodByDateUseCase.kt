package com.example.apod.domain

import com.example.apod.data.APODRepository
import com.example.apod.data.model.APODResponse
import javax.inject.Inject

class GetApodByDateUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (date: String) : APODResponse? = repository.getAPODByDate(date)
}