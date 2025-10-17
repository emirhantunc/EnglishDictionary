package com.example.domain.repository

import com.example.domain.model.network.Word
import com.example.domain.model.room.FolderRoom
import com.example.domain.model.room.WordRoom
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getWordMeaning(word: String): List<Word>
    suspend fun insertMeaning(wordRoom: WordRoom)
    fun getAllFolders(): Flow<List<FolderRoom>>
}