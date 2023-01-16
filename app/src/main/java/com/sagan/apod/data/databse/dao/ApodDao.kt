package com.sagan.apod.data.databse.dao

import androidx.room.*
import com.sagan.apod.data.databse.entities.ApodEntity

@Dao
interface ApodDao {

    @Query("SELECT * FROM apod_table ORDER BY date DESC")
    fun getAll(): List<ApodEntity>

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    fun insertAll(vararg apods: List<ApodEntity>)

    @Query("DELETE FROM apod_table")
    fun delete(apods: List<ApodEntity>)

}