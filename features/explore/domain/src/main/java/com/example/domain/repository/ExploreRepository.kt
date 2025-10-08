package com.example.domain.repository

import com.example.domain.model.Word
import com.example.domain.model.Folder
import kotlinx.coroutines.flow.Flow

interface ExploreRepository {
     fun getFolders(): Flow<List<Folder>>
    suspend fun insertFolder(folder: Folder)
    suspend fun deleteFolder(folder: Folder)
    suspend fun getWords(folderId: Int): List<Word>
}