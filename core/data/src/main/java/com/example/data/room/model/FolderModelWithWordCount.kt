package com.example.data.room.model

import androidx.room.Embedded

data class FolderModelWithWordCount(
    @Embedded
    val folder: FolderModel,
    val wordCount: Int
)