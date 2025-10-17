package com.example.domain.usecases

import com.example.domain.model.QuizFolder
import com.example.domain.repository.QuizRepository

class GetQuizFolderByIdUseCase(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(id: Int): QuizFolder {
        return repository.getFolderById(id)
    }
}