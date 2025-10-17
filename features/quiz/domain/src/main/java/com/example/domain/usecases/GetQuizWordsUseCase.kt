package com.example.domain.usecases

import com.example.domain.model.QuizWord
import com.example.domain.repository.QuizRepository

class GetQuizWordsUseCase(
    private val repository: QuizRepository
) {
    suspend operator fun invoke(folderId: Int): List<QuizWord> {
        return repository.getQuizWords(folderId)
    }
}