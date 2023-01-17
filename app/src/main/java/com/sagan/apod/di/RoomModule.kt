package com.sagan.apod.di

import android.content.Context
import androidx.room.Room
import com.sagan.apod.data.databse.ApodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APOD_DATABASE_NAME = "apod_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, ApodDatabase::class.java, APOD_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideApodDao(db: ApodDatabase) = db.getApodDao()

}