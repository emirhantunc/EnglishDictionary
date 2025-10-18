package com.example.data.mapper

import com.example.data.room.model.FolderModel
import com.example.domain.model.CreateFolder


fun CreateFolder.toFolderModel(): FolderModel {
    return FolderModel(
        id = id,
        name = name
    )
}