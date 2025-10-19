package com.example.domain.usecases

import com.example.domain.model.QuizFolder
import com.example.domain.model.QuizFolderWithCount
import com.example.domain.repository.QuizRepository
import kotlinx.coroutines.flow.Flow

class GetQuizFoldersUseCase(
    private val repository: QuizRepository
) {
     operator fun invoke():Flow<List<QuizFolderWithCount>>{
        return repository.getQuizFolders()
    }
}