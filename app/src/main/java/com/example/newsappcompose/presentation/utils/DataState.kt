package com.example.newsappcompose.presentation.utils

sealed class DataState{
    data class Toast(val message: String): DataState()
}
