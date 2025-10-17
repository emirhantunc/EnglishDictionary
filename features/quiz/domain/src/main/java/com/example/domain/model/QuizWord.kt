package com.example.domain.model

data class QuizWord(
    val id: Int,
    val word: String,
    val definition: String,
    val folderId: Int,
    val audio: String
)
