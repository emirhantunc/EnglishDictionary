package com.example.domain.usecases.network

import com.example.domain.model.network.Word
import com.example.domain.repository.DictionaryRepository

class GetWordMeaningUseCase(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(word: String): List<Word> {
        return repository.getWordMeaning(word = word)
    }
}