package com.example.newsappcompose.presentation.di

import com.example.newsappcompose.data.remote.retrofit.ApiService
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.domain.usecase.GetNewsUseCase
import com.example.newsappcompose.domain.usecase.GetSearchUseCase
import com.example.newsappcompose.domain.usecase.NewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppInjection {

    @Provides
    @Singleton
    fun provideNewsRepository(
        apiService: ApiService
    ): NewsRepository {
        return NewsRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ): NewsUseCase {
        return NewsUseCase(
            getNewsUseCase = GetNewsUseCase(newsRepository),
            getSearchUseCase = GetSearchUseCase(newsRepository)
        )
    }


}