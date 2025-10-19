package com.example.domain.usecases.room

import com.example.domain.model.room.SearchFolder
import com.example.domain.model.room.SearchFolderWithCount
import com.example.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetAllFoldersUseCase(
    private val repository: SearchRepository
) {
    operator fun invoke(): Flow<List<SearchFolderWithCount>>{
        return repository.getAllFolders()
    }
}