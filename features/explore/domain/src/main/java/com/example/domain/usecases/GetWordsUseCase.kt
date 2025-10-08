package com.example.domain.usecases

import com.example.domain.model.Word
import com.example.domain.repository.ExploreRepository

class GetWordsUseCase(
    private val repository: ExploreRepository
) {
    suspend operator fun invoke(folderId: Int): List<Word> {
        return repository.getWords(folderId = folderId)
    }
}