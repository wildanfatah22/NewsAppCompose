package com.example.newsappcompose.domain.usecase

import com.example.newsappcompose.data.local.NewsDao
import com.example.newsappcompose.domain.model.Article


class GetArticleUseCase (
    private val newsDao: NewsDao
) {
    suspend operator fun invoke(url: String): Article?{
        return newsDao.getArticle(url = url)
    }

}