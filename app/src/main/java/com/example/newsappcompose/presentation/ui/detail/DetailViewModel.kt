package com.example.newsappcompose.presentation.ui.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.usecase.NewsUseCase
import com.example.newsappcompose.presentation.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    var sideEffect by mutableStateOf<DataState?>(null)
        private set

    private var _isBookmarked = mutableStateOf(false)
    val isBookmarked: State<Boolean> = _isBookmarked

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.getArticleUseCase(url = event.article.url)
                    if (article == null){
                        upsertArticle(article = event.article)
                        _isBookmarked.value = true
                    }else{
                        deleteArticle(article = event.article)
                        _isBookmarked.value = false
                    }
                }
            }
            is DetailEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCase.deleteArticleUseCase(article = article)
        sideEffect = DataState.Toast("Article removed from bookmark")
    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCase.upsertArticleUseCase(article = article)
        sideEffect = DataState.Toast("Article added to bookmark")
    }

    fun loadBookmarkStatus(article: Article) {
        viewModelScope.launch {
            val bookmarkedArticle = newsUseCase.getArticleUseCase(url = article.url)
            _isBookmarked.value = bookmarkedArticle != null
        }
    }

}