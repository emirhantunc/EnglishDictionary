package com.example.domain.model.room

data class SearchRoom(
    val id: Int = 0,
    val word: String,
    val folderId: Int,
    val definition: String,
    val audio: String
)