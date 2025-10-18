package com.example.domain.usecases

import com.example.domain.model.CreateFolder
import com.example.domain.repository.CreateFolderRepository

class CreateFolderUseCase(
    private val repository: CreateFolderRepository
) {
    suspend operator fun invoke(folder: CreateFolder) {
        repository.createFolder(list = folder)
    }
}