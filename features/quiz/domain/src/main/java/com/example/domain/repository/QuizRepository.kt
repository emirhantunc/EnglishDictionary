package com.example.domain.repository

import com.example.domain.model.QuizFolder
import com.example.domain.model.QuizFolderWithCount
import com.example.domain.model.QuizWord
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun getQuizFolders(): Flow<List<QuizFolderWithCount>>
    suspend fun getFolderById(id: Int): QuizFolder
    suspend fun getQuizWords(folderId: Int): List<QuizWord>
}