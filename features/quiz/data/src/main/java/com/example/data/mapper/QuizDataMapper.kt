package com.example.data.mapper

import com.example.data.room.model.FolderModel
import com.example.data.room.model.FolderModelWithWordCount
import com.example.data.room.model.WordModel
import com.example.domain.model.QuizFolder
import com.example.domain.model.QuizFolderWithCount
import com.example.domain.model.QuizWord

fun FolderModel.toFolder(): QuizFolder {
    return QuizFolder(
        id = id,
        name = name
    )
}

fun FolderModelWithWordCount.toFolderWithCount(): QuizFolderWithCount {
    return QuizFolderWithCount(
        folder = folder.toFolder(),
        wordCount = wordCount
    )
}

fun List<FolderModelWithWordCount>.toFolderWithCountList(): List<QuizFolderWithCount> {
    return this.map { it.toFolderWithCount() }
}

fun WordModel.toWord(): QuizWord {
    return QuizWord(
        id = id,
        word = word,
        definition = definition,
        folderId = folderId,
        audio = audio
    )
}

fun List<WordModel>.toWordList(): List<QuizWord> {
    return this.map { it.toWord() }
}