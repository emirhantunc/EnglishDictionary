package com.example.data.impl

import com.example.data.mapper.toFolderModel
import com.example.data.room.dao.DictionaryDao
import com.example.domain.model.CreateFolder
import com.example.domain.repository.CreateFolderRepository
import javax.inject.Inject

class CreateFolderRepositoryImpl @Inject constructor(
    private val dao: DictionaryDao
) : CreateFolderRepository {

    override suspend fun createFolder(list: CreateFolder) {
        dao.insertFolder(list.toFolderModel())
    }
}