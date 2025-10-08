package com.example.data.di

import com.example.data.impl.DictionaryRepositoryImpl
import com.example.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindingDictionaryRepository() {

    @Binds
    @ViewModelScoped
    abstract fun bindingRepositoryModule(impl: DictionaryRepositoryImpl): DictionaryRepository
}