package com.example.newsappcompose.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.data.local.NewsDatabase
import com.example.newsappcompose.data.local.NewsTypeConvertor
import com.example.newsappcompose.data.remote.retrofit.ApiService
import com.example.newsappcompose.data.repository.NewsRepositoryImpl
import com.example.newsappcompose.domain.repository.NewsRepository
import com.example.newsappcompose.domain.usecase.DeleteArticleUseCase
import com.example.newsappcompose.domain.usecase.GetArticleUseCase
import com.example.newsappcompose.domain.usecase.GetArticlesUseCase
import com.example.newsappcompose.domain.usecase.GetNewsUseCase
import com.example.newsappcompose.domain.usecase.GetSearchUseCase
import com.example.newsappcompose.domain.usecase.NewsUseCase
import com.example.newsappcompose.domain.usecase.UpsertArticleUseCase
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
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCase {
        return NewsUseCase(
            getNewsUseCase = GetNewsUseCase(newsRepository),
            getSearchUseCase = GetSearchUseCase(newsRepository),
            getArticlesUseCase = GetArticlesUseCase(newsDao),
            deleteArticleUseCase = DeleteArticleUseCase(newsDao),
            upsertArticleUseCase = UpsertArticleUseCase(newsDao),
            getArticleUseCase = GetArticleUseCase(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = "news_db"
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao


}