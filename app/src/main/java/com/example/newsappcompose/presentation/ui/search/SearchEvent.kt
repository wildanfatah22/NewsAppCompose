package com.example.newsappcompose.presentation.ui.search

sealed class SearchEvent {
    data class UpdateSearch(val searchQuery: String): SearchEvent()

    object SearchNews: SearchEvent()

}
