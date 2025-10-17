package com.example.domain.usecases

data class QuizUseCases(
    val getQuizWordsUseCase: GetQuizWordsUseCase,
    val getQuizFoldersUseCase: GetQuizFoldersUseCase,
    val getQuizFolderByIdUseCase: GetQuizFolderByIdUseCase
)
