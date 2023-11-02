package com.example.newsappcompose.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed class Route(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {
    object HomeScreen : Route(route = "homeScreen")

    object SearchScreen : Route(route = "searchScreen")

    object FavoriteScreen : Route(route = "favoriteScreen")

    object UserScreen : Route(route = "userScreen")

    object DetailScreen : Route(route = "detailScreen")

    object AppStartNavigation : Route(route = "appStartNavigation")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigator")
}
