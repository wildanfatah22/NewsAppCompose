package com.example.newsappcompose.presentation.ui.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newsappcompose.domain.model.Article
import com.example.newsappcompose.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.UpdateSearch -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is SearchEvent.SearchNews -> {
                searchNews()
            }
        }
    }

    private fun searchNews() {
        val articles = newsUseCase.getSearchUseCase(
            searchQuery = _state.value.searchQuery,
            sources = listOf("bbc-news", "abc-news", "al-jazeera-english")
        ).cachedIn(viewModelScope)
        _state.value = _state.value.copy(articles = articles)
    }

}

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)