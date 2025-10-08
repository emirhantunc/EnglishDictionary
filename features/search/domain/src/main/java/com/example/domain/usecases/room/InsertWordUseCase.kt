package com.example.domain.usecases.room

import com.example.domain.model.room.WordRoom
import com.example.domain.repository.DictionaryRepository

class InsertWordUseCase(
    private val repository: DictionaryRepository
) {
    suspend operator fun invoke(wordRoom: WordRoom) {
        repository.insertMeaning(wordRoom)
    }
}