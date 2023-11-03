package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.domain.model.Article
import kotlinx.coroutines.flow.Flow

class GetArticlesUseCase(
    private val newsDao: NewsDao
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsDao.getArticles()
    }

}