package com.sagan.apod.data.databse.dao

import androidx.room.*
import com.sagan.apod.data.databse.entities.ApodEntity

@Dao
interface ApodDao {

    @Query("SELECT * FROM apod_table ORDER BY date DESC")
    suspend fun getAll(): List<ApodEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAll(apods: List<ApodEntity>)

    @Query("DELETE FROM apod_table")
    suspend fun delete()

    @Query("SELECT (SELECT COUNT(*) FROM apod_table) == 0")
    suspend fun isEmpty(): Boolean

}