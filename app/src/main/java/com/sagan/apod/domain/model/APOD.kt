package com.sagan.apod.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sagan.apod.data.databse.entities.ApodEntity
import com.sagan.apod.data.model.APODModel

data class APOD (
    var copyright: String? = null,
    var date: String? = null,
    var explanation: String? = null,
    var hdurl: String? = null,
    var mediaType: String? = null,
    var title: String? = null,
    var url: String? = null,
    var thumbnail_url: String? = null
)

//Mapper
fun APODModel.toDomain() = APOD(copyright, date, explanation, hdurl, mediaType, title, url, thumbnail_url)

fun ApodEntity.toDomain() = APOD(copyright, date, explanation, hdurl, mediaType, title, url, thumbnail_url)