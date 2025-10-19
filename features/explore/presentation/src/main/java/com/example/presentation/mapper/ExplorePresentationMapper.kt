package com.example.presentation.mapper

import android.util.Log
import com.example.domain.model.Folder
import com.example.domain.model.FolderWithWordCount
import com.example.domain.model.Word
import com.example.presentation.model.FolderState
import com.example.presentation.model.FolderWithCountState
import com.example.presentation.model.WordState

fun Folder.toFolderState(): FolderState {
    return FolderState(
        id = id,
        name = name
    )
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

fun FolderWithWordCount.toFolderWithCountState(): FolderWithCountState {
    return FolderWithCountState(
        folderState = folder.toFolderState(),
        wordCount = wordCount
    )
}

fun List<FolderWithWordCount>.toFolderWithCountStateList(): List<FolderWithCountState> {
    return this.map { it.toFolderWithCountState() }
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


