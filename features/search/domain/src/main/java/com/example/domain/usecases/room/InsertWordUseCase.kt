package com.example.domain.usecases.room

import com.example.domain.model.room.SearchRoom
import com.example.domain.repository.SearchRepository

class InsertWordUseCase(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(searchRoom: SearchRoom) {
        repository.insertMeaning(searchRoom)
    }
}