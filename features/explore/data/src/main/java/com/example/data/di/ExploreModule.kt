package com.example.data.di

import com.example.domain.repository.ExploreRepository
import com.example.domain.usecases.DeleteFolderUseCase
import com.example.domain.usecases.GetFoldersUseCase
import com.example.domain.usecases.GetWordsUseCase
import com.example.domain.usecases.InsertFolderUseCase
import com.example.domain.usecases.UseCasesExplore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object ExploreModule {

    @ViewModelScoped
    @Provides
    fun provideUseCasesExplore(repository: ExploreRepository): UseCasesExplore {
        return UseCasesExplore(
            getWordsUseCase = GetWordsUseCase(repository),
            getFoldersUseCase = GetFoldersUseCase(repository),
            insertFoldersUseCase = InsertFolderUseCase(repository),
            deleteFolderUseCase = DeleteFolderUseCase(repository)
        )
    }
}