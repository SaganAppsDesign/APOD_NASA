package com.sagan.apod.di

import com.sagan.apod.data.network.ApiInterceptor
import com.sagan.apod.data.network.ApodAPIClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/planetary/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(apiInterceptor = ApiInterceptor()))
            .build()

    }

    @Singleton
    @Provides
    fun provideOkHttpClient(apiInterceptor: ApiInterceptor): OkHttpClient{
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(apiInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApodAPIClient {
        return retrofit.create(ApodAPIClient::class.java)
    }

}