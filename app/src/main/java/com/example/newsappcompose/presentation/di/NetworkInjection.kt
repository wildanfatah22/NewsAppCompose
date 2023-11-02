package com.example.newsappcompose.presentation.di

import com.example.newsappcompose.data.remote.retrofit.ApiService
import com.example.newsappcompose.presentation.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkInjection {

    @Provides
    @Singleton
    fun provideApiInstance(): ApiService {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}