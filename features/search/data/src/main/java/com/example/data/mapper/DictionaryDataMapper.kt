package com.example.data.mapper

import com.example.data.network.model.DefinitionResponse
import com.example.data.network.model.MeaningsResponse
import com.example.data.network.model.PhoneticResponse
import com.example.data.network.model.WordResponse
import com.example.data.room.model.FolderModel
import com.example.data.room.model.FolderModelWithWordCount
import com.example.data.room.model.WordModel
import com.example.domain.model.network.Definitions
import com.example.domain.model.network.Meanings
import com.example.domain.model.network.Phonetic
import com.example.domain.model.network.Word
import com.example.domain.model.room.SearchFolder
import com.example.domain.model.room.SearchFolderWithCount
import com.example.domain.model.room.SearchRoom


fun PhoneticResponse.toPhonetic(): Phonetic {
    return Phonetic(
        phonetic = phonetic,
        audio = audio
    )
}

fun List<PhoneticResponse>.toPhoneticList(): List<Phonetic> {
    return this.map { it.toPhonetic() }
}

fun DefinitionResponse.toDefinition(): Definitions {
    return Definitions(
        definition = definition,
        example = example
    )
}

fun List<DefinitionResponse>.toDefinitionList(): List<Definitions> {
    return this.map { it.toDefinition() }
}

fun MeaningsResponse.toMeanings(): Meanings {
    return Meanings(
        definitions = definitionResponse.toDefinitionList(),
        partOfSpeech = partOfSpeech
    )
}

fun List<MeaningsResponse>.toMeaningsList(): List<Meanings> {
    return this.map { it.toMeanings() }
}

fun WordResponse.toWord(): Word {
    return Word(
        word = word,
        phonetics = phonetics?.toPhoneticList() ?: emptyList(),
        meanings = meanings?.toMeaningsList() ?: emptyList()
    )
}

fun List<WordResponse>.toWordList(): List<Word> {
    return this.map { it.toWord() }
}

fun SearchRoom.toWordModel(): WordModel {
    return WordModel(
        id = id,
        word = word,
        folderId = folderId,
        definition = definition,
        audio = audio
    )
}

fun FolderModel.toFolder(): SearchFolder {
    return SearchFolder(
        id = id,
        name = name
    )
}

fun FolderModelWithWordCount.toFolderWithCount(): SearchFolderWithCount {
    return SearchFolderWithCount(
        folder = folder.toFolder(),
        wordCount = wordCount
    )
}

fun List<FolderModelWithWordCount>.totoFolderWithCountList(): List<SearchFolderWithCount> {
    return this.map { it.toFolderWithCount() }
}


