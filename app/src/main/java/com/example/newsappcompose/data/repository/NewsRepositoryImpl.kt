package com.example.newsappcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newsappcompose.data.remote.paging.NewsPagingSource
import com.example.newsappcompose.data.remote.retrofit.ApiService
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val apiService: ApiService
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(apiService = apiService, sources = sources.joinToString(separator = ","))
            }
        ).flow
    }

}