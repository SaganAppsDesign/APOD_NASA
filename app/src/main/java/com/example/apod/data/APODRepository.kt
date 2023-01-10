package com.example.apod.data

import com.example.apod.data.model.APODResponse
import com.example.apod.data.network.APODService

class APODRepository {

    private val api = APODService()

    suspend fun getAPODsByCount(query: String): List<APODResponse?> {
        return api.getAPODSByCount(query)
    }
}