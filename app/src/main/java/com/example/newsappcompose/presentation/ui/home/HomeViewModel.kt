package com.example.newsappcompose.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.newsappcompose.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    val news = newsUseCase.getNewsUseCase(
        sources = listOf("bbc-news","abc-news","al-jazeera-english")
    ).cachedIn(viewModelScope)

}