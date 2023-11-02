package com.example.newsappcompose.data.remote.response

import com.example.newsappcompose.domain.model.Article


data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)