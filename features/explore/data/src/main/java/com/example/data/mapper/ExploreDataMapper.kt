package com.example.data.mapper

import com.example.data.room.model.FolderModel
import com.example.data.room.model.FolderModelWithWordCount
import com.example.data.room.model.WordModel
import com.example.domain.model.Folder
import com.example.domain.model.FolderWithWordCount
import com.example.domain.model.Word

fun Folder.toFolderModel(): FolderModel {
    return FolderModel(
        id = id,
        name = name
    )
}

fun FolderModel.toFolder(): Folder {
    return Folder(
        id = id,
        name = name
    )
}

fun List<FolderModel>.toFolderList(): List<Folder> {
    return this.map { it.toFolder() }
}

fun FolderModelWithWordCount.toFolderWithCount(): FolderWithWordCount {
    return FolderWithWordCount(
        folder = folder.toFolder(),
        wordCount = wordCount
    )
}

fun List<FolderModelWithWordCount>.toFolderWithCountList(): List<FolderWithWordCount> {
    return this.map { it.toFolderWithCount() }
}

fun WordModel.toWord(): Word {
    return Word(
        id = id,
        word = word,
        definition = definition,
        folderId = folderId,
        audio = audio
    )
}

fun List<WordModel>.toWordList(): List<Word> {
    return this.map { it.toWord() }
}