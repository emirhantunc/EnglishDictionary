package com.example.domain.usecases.room

import com.example.domain.model.room.FolderRoom
import com.example.domain.model.room.WordRoom
import com.example.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow

class GetAllFoldersUseCase(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(): Flow<List<FolderRoom>>{
        return repository.getAllFolders()
    }
}