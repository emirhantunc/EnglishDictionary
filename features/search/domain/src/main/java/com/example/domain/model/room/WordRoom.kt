package com.example.domain.model.room

data class WordRoom(
    val id: Int = 0,
    val word: String,
    val folderId: Int,
    val definition: String,
    val audio: String
)