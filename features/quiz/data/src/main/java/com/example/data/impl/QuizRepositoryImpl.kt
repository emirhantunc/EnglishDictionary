package com.example.data.impl

import com.example.data.mapper.toFolder
import com.example.data.mapper.toFolderWithCountList
import com.example.data.room.dao.DictionaryDao
import com.example.data.mapper.toWordList
import com.example.domain.model.QuizFolder
import com.example.domain.model.QuizFolderWithCount
import com.example.domain.model.QuizWord
import com.example.domain.repository.QuizRepository
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val dao: DictionaryDao
) : QuizRepository {

    override fun getQuizFolders(): Flow<List<QuizFolderWithCount>> {
        return dao.getAllFolders().map { folder ->
            folder.toFolderWithCountList()
        }
    }

    override suspend fun getFolderById(id: Int): QuizFolder {
        return dao.getFolderById(id).toFolder()
    }

    override suspend fun getQuizWords(folderId: Int): List<QuizWord> {
        return dao.getWords(folderId).toWordList()
    }
}