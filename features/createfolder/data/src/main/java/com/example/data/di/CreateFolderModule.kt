package com.example.data.di

import com.example.domain.repository.CreateFolderRepository
import com.example.domain.usecases.CreateFolderUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CreateFolderModule {

    @ViewModelScoped
    @Provides
    fun provideCreateFolderUseCase(repository: CreateFolderRepository): CreateFolderUseCase =
        CreateFolderUseCase(repository = repository)
}