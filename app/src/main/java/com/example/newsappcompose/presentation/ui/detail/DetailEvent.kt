package com.example.newsappcompose.presentation.ui.detail

import com.example.newsappcompose.domain.model.Article

sealed class DetailEvent{
    data class UpsertDeleteArticle(val article: Article) : DetailEvent()

    object RemoveSideEffect : DetailEvent()


}
