package com.example.data.di

import com.example.data.impl.CreateFolderRepositoryImpl
import com.example.domain.repository.CreateFolderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class BindingCreateRepository {
    @Binds
    @ViewModelScoped
    abstract fun bindingCreateFolderRepository(
        impl: CreateFolderRepositoryImpl
    ) : CreateFolderRepository
}