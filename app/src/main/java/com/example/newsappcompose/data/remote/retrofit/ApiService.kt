package com.example.newsappcompose.data.remote.retrofit

import com.example.newsappcompose.BuildConfig
import com.example.newsappcompose.data.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun getNews(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): NewsResponse


}