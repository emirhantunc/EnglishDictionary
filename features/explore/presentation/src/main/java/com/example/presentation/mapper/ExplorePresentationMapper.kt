package com.example.presentation.mapper

import android.util.Log
import com.example.domain.model.Folder
import com.example.domain.model.Word
import com.example.presentation.model.FolderState
import com.example.presentation.model.WordState

fun Folder.toFolderState(): FolderState {
    return FolderState(
        id = id,
        name = name
    )
}

fun List<Folder>.toFolderStateList(): List<FolderState> {
    return this.map { it.toFolderState() }
}

fun Word.toWordState(): WordState {
    return WordState(
        id = id,
        word = word,
        folderId = folderId,
        definition = definition,
        audio = audio
    )
}

fun List<Word>.toWordStateList(): List<WordState> {
    return this.map { it.toWordState() }
}

fun FolderState.toFolder(): Folder {
    return Folder(
        id = id,
        name = name
    )
}


