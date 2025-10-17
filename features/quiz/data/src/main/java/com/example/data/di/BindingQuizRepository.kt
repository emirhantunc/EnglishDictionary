package com.example.data.di

import com.example.data.impl.QuizRepositoryImpl
import com.example.domain.repository.QuizRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class BindingQuizRepository() {

    @Binds
    @ViewModelScoped
    abstract fun bindingQuizRepository(impl: QuizRepositoryImpl) : QuizRepository
}