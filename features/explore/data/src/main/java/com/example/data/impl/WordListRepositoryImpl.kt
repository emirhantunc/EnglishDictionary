package com.example.data.impl

import com.example.data.mapper.toFolderList
import com.example.data.mapper.toFolderModel
import com.example.data.mapper.toWordList
import com.example.data.room.dao.DictionaryDao
import com.example.domain.model.Folder
import com.example.domain.model.Word
import com.example.domain.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordListRepositoryImpl @Inject constructor
    (private val dao: DictionaryDao) :
    ExploreRepository {


    override  fun getFolders(): Flow<List<Folder>> {
        return dao.getAllFolders().map { list ->
            list.toFolderList()
        }
    }

    override suspend fun insertFolder(folder: Folder) {
        dao.insertFolder(folder.toFolderModel())

    }

    override suspend fun deleteFolder(folder: Folder) {
         dao.deleteFolder(folder.toFolderModel())
    }

    override suspend fun getWords(folderId: Int): List<Word> {
        return dao.getWords(folderId = folderId).toWordList()
    }
}