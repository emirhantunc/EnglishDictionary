package com.example.presentation.mapper

import com.example.domain.model.QuizFolder
import com.example.domain.model.QuizWord
import com.example.presentation.model.QuizFolderState
import com.example.presentation.model.QuizWordState


fun QuizFolder.toFolderState(): QuizFolderState {
    return QuizFolderState(
        id = id,
        name = name
    )
}

fun List<QuizFolder>.toFolderStateList(): List<QuizFolderState> {
    return this.map {
        it.toFolderState()
    }
}

fun QuizWord.toWordState(): QuizWordState {
    return QuizWordState(
        id = id,
        word = word,
        definition = definition,
        folderId = folderId,
        audioUrl = audio
    )
}

fun List<QuizWord>.toWordStateList(): List<QuizWordState> {
    return this.map { it.toWordState() }
}