package com.example.presentation.mapper

import com.example.domain.model.CreateFolder
import com.example.presentation.model.CreateFolderPresentation


fun CreateFolderPresentation.toCreateFolder(): CreateFolder {
    return CreateFolder(
        id = id,
        name = name
    )
}