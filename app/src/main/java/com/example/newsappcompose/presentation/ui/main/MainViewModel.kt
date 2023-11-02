package com.example.newsappcompose.presentation.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsappcompose.presentation.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel


class MainViewModel: ViewModel() {
    private val _startDestination = mutableStateOf(Route.NewsNavigation.route)
    val startDestination: State<String> = _startDestination


}