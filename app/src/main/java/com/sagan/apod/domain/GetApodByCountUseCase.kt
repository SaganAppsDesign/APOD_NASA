package com.sagan.apod.domain

import com.sagan.apod.data.APODRepository
import com.sagan.apod.data.model.APODResponse
import javax.inject.Inject

class GetApodByCountUseCase @Inject constructor(private val repository : APODRepository){
    suspend operator fun invoke (query: String) : List<APODResponse?> = repository.getAPODsByCount(query)
}