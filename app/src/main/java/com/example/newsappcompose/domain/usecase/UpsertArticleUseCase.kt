package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.domain.model.Article


class UpsertArticleUseCase(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article = article)
    }

}