package com.example.domain.usecases

import com.example.domain.usecases.network.GetWordMeaningUseCase
import com.example.domain.usecases.room.GetAllFoldersUseCase
import com.example.domain.usecases.room.InsertWordUseCase

data class UseCasesSearch(
    val getWordMeaningUseCase: GetWordMeaningUseCase,
    val insertWordUseCase: InsertWordUseCase,
    val getAllFoldersUseCase: GetAllFoldersUseCase,
)
