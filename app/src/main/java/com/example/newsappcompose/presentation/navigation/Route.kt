package com.example.newsappcompose.presentation.navigation


sealed class Route(
    val route: String,
) {
    object HomeScreen : Route(route = "homeScreen")

    object BookMarkScreen : Route(route = "bookMarkScreen")

    object UserScreen : Route(route = "userScreen")

    object DetailScreen : Route(route = "detailScreen")

    object NewsNavigation : Route(route = "newsNavigation")

    object NewsNavigatorScreen : Route(route = "newsNavigator")
}
