package com.example.data.di

import com.example.data.impl.WordListRepositoryImpl
import com.example.domain.repository.ExploreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindingExploreRepository() {

    @Binds
    @ViewModelScoped
    abstract fun bindingRepositoryModule(impl: WordListRepositoryImpl): ExploreRepository
}