package com.example.domain.usecases

import com.example.domain.model.Folder
import com.example.domain.repository.ExploreRepository

class InsertFolderUseCase(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(folder: Folder) {
        repository.insertFolder(folder)
    }
}