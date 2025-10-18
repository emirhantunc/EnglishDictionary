package com.example.domain.repository

import com.example.domain.model.CreateFolder

interface CreateFolderRepository {
    suspend fun createFolder(list: CreateFolder)
}