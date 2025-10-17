package com.example.domain.usecases.network

import com.example.domain.model.network.Word
import com.example.domain.repository.SearchRepository

class GetWordMeaningUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(word: String): List<Word> {
        return repository.getWordMeaning(word = word)
    }
}