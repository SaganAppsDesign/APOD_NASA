package com.nasa.apod.domain

import com.nasa.apod.data.APODRepository
import com.nasa.apod.data.model.APODResponse
import javax.inject.Inject

class GetApodByCountUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (query: String) : List<APODResponse?> = repository.getAPODsByCount(query)
}