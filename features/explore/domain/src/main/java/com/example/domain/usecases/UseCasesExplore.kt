package com.example.domain.usecases

data class UseCasesExplore(
    val getWordsUseCase: GetWordsUseCase,
    val getFoldersUseCase: GetFoldersUseCase,
    val insertFoldersUseCase: InsertFolderUseCase,
    val deleteFolderUseCase: DeleteFolderUseCase
)
