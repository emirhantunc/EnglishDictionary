package com.example.domain.repository

import com.example.domain.model.network.Word
import com.example.domain.model.room.SearchFolder
import com.example.domain.model.room.SearchFolderWithCount
import com.example.domain.model.room.SearchRoom
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getWordMeaning(word: String): List<Word>
    suspend fun insertMeaning(searchRoom: SearchRoom)
    fun getAllFolders(): Flow<List<SearchFolderWithCount>>
}