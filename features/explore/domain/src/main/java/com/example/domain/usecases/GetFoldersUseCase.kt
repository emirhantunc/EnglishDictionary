package com.example.domain.usecases

import com.example.domain.model.Folder
import com.example.domain.model.FolderWithWordCount
import com.example.domain.repository.ExploreRepository
import kotlinx.coroutines.flow.Flow

class GetFoldersUseCase(
    private val repository: ExploreRepository
) {
     operator fun invoke(): Flow<List<FolderWithWordCount>> {
        return repository.getFolders()
    }
}