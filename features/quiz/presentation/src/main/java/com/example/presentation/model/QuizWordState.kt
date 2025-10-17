package com.example.presentation.model

data class QuizWordState(
    val id: Int,
    val word: String,
    val definition: String,
    val folderId: Int,
    val audioUrl: String
)
