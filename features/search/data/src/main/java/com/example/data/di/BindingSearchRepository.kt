package com.example.data.di

import com.example.data.impl.SearchRepositoryImpl
import com.example.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindingSearchRepository() {

    @Binds
    @ViewModelScoped
    abstract fun bindingSearchRepository(impl: SearchRepositoryImpl): SearchRepository
}