package com.example.data.di

import com.example.domain.repository.QuizRepository
import com.example.domain.usecases.GetQuizFolderByIdUseCase
import com.example.domain.usecases.GetQuizFoldersUseCase
import com.example.domain.usecases.GetQuizWordsUseCase
import com.example.domain.usecases.QuizUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object QuizModule {

    @Provides
    @ViewModelScoped
    fun provideQuizUseCases(repository: QuizRepository): QuizUseCases {
        return QuizUseCases(
            getQuizFoldersUseCase = GetQuizFoldersUseCase(repository = repository),
            getQuizWordsUseCase = GetQuizWordsUseCase(repository = repository),
            getQuizFolderByIdUseCase = GetQuizFolderByIdUseCase(repository = repository)
        )
    }
}