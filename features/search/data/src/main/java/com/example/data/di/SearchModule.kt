package com.example.data.di

import com.example.domain.repository.SearchRepository
import com.example.domain.usecases.UseCasesSearch
import com.example.domain.usecases.network.GetWordMeaningUseCase
import com.example.domain.usecases.room.GetAllFoldersUseCase
import com.example.domain.usecases.room.InsertWordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object SearchModule {

    @ViewModelScoped
    @Provides
    fun provideUseCasesSearch(repository: SearchRepository): UseCasesSearch {
        return UseCasesSearch(
            getWordMeaningUseCase = GetWordMeaningUseCase(repository = repository),
            insertWordUseCase = InsertWordUseCase(repository = repository),
            getAllFoldersUseCase = GetAllFoldersUseCase(repository = repository)

        )
    }
}