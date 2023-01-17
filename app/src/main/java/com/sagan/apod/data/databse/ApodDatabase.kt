package com.sagan.apod.data.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sagan.apod.data.databse.dao.ApodDao
import com.sagan.apod.data.databse.entities.ApodEntity

@Database(entities = [ApodEntity::class], version = 1)
abstract class ApodDatabase : RoomDatabase() {
    abstract fun getApodDao(): ApodDao
}