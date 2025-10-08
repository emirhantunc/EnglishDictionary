package com.example.presentation.model.room

data class WordRoomPresentation(
    val id: Int = 0,
    val word: String,
    val folderId: Int,
    val definition: String
)
