package com.example.newsappcompose.domain.usecase

data class NewsUseCase(
    val getNewsUseCase: GetNewsUseCase,
    val getSearchUseCase: GetSearchUseCase,
    val getArticlesUseCase: GetArticlesUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val getArticleUseCase: GetArticleUseCase
)