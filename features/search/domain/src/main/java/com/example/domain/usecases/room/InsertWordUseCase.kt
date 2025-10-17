package com.example.domain.usecases.room

import com.example.domain.model.room.WordRoom
import com.example.domain.repository.SearchRepository

class InsertWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(wordRoom: WordRoom) {
        repository.insertMeaning(wordRoom)
    }
}