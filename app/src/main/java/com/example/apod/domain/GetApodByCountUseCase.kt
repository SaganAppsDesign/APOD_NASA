package com.example.apod.domain

import com.example.apod.data.APODRepository
import com.example.apod.data.model.APODResponse
import javax.inject.Inject

class GetApodByCountUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (query: String) : List<APODResponse?> = repository.getAPODsByCount(query)
}