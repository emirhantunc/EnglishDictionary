package com.example.presentation.ui.routes

sealed class ExploreScreenRoutes(val route: String) {
    object Explore : ExploreScreenRoutes("explore")
    object Word : ExploreScreenRoutes("word/{folderId}") {
        fun createRoute(folderId: Int): String = "word/$folderId"
    }
}