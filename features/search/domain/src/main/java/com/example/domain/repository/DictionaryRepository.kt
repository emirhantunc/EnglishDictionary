package com.example.domain.repository

import com.example.domain.model.network.Word
import com.example.domain.model.room.FolderRoom
import com.example.domain.model.room.WordRoom
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {
    suspend fun getWordMeaning(word: String): List<Word>
    suspend fun insertMeaning(wordRoom: WordRoom)
    suspend fun getAllFolders(): Flow<List<FolderRoom>>
}