package com.example.apod.domain

import androidx.appcompat.app.AppCompatActivity
import com.example.apod.data.APODRepository
import com.example.apod.data.model.APODResponse

class GetApodByCountUseCase: AppCompatActivity() {
    private val repository = APODRepository()
    suspend operator fun invoke (query: String):List<APODResponse?> = repository.getAPODsByCount(query)
}