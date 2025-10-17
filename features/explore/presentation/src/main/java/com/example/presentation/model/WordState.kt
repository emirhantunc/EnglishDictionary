package com.example.presentation.model

data class WordState(
    val id: Int,
    val word: String,
    val definition: String,
    val folderId: Int,
    val audio: String
)
