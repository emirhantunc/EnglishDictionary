package com.example.presentation.ui.routes

const val FOLDER_ID = "folderId"

sealed class QuizScreenRoutes(val route: String) {

    object Quiz : QuizScreenRoutes(route = "quiz")
    object QuizFinished : QuizScreenRoutes(route = "quiz_finished")

    object WordMemory : QuizScreenRoutes(route = "word_memory/{$FOLDER_ID}") {
        fun createRoute(folderId: Int): String = "word_memory/$folderId"
    }

    object FlashCard : QuizScreenRoutes(route = "flash_card/{$FOLDER_ID}") {
        fun createRoute(folderId: Int): String = "flash_card/$folderId"
    }

    object Audio : QuizScreenRoutes(route = "audio/{$FOLDER_ID}") {
        fun createRoute(folderId: Int): String = "audio/$folderId"
    }

    object Selection : QuizScreenRoutes(route = "selection/{$FOLDER_ID}"){
        fun createRoute(folderId: Int) : String = "selection/$folderId"
    }
}
