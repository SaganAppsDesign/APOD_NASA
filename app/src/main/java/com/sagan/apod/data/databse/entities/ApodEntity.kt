package com.sagan.apod.data.databse.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sagan.apod.domain.model.APOD

@Entity(tableName = "apod_table")
data class ApodEntity(
    @PrimaryKey (autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "copyright") val copyright: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "explanation") val explanation: String?,
    @ColumnInfo(name = "hdurl") val hdurl: String?,
    @ColumnInfo(name = "media_type") val mediaType: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "thumbnail_url") val thumbnail_url: String?,
)

//Mapper
fun APOD.toDataBase() = ApodEntity(copyright = copyright,
                                   date = date, explanation = explanation,
                                   hdurl = hdurl,
                                   mediaType = mediaType,
                                   title = title,
                                   url = url,
                                   thumbnail_url = thumbnail_url)
