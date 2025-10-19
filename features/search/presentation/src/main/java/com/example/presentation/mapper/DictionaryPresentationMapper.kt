package com.example.presentation.mapper

import com.example.domain.model.network.Definitions
import com.example.domain.model.network.Meanings
import com.example.domain.model.network.Phonetic
import com.example.domain.model.network.Word
import com.example.domain.model.room.SearchFolder
import com.example.domain.model.room.SearchFolderWithCount
import com.example.domain.model.room.SearchRoom
import com.example.presentation.model.network.DefinitionState
import com.example.presentation.model.network.MeaningState
import com.example.presentation.model.network.PhoneticState
import com.example.presentation.model.network.WordState
import com.example.presentation.model.room.SearchFolderState
import com.example.presentation.model.room.SearchFolderWithCountState
import com.example.presentation.model.room.SearchWordPresentation

fun Phonetic.toPhoneticState(): PhoneticState {
    return PhoneticState(
        phonetic = phonetic,
        audio = audio
    )
}

fun List<Phonetic>.toPhoneticStateList(): List<PhoneticState> {
    return this.map { it.toPhoneticState() }
}

fun Definitions.toDefinitionState(): DefinitionState {
    return DefinitionState(
        definition = definition,
        example = example
    )
}

fun Meanings.toMeaningState(): MeaningState {
    return MeaningState(
        definitions = definitions.toDefinitionStateList(),
        partOfSpeech = partOfSpeech
    )
}

fun List<Meanings>.toMeaningStateList(): List<MeaningState> {
    return this.map { it.toMeaningState() }
}

fun List<Definitions>.toDefinitionStateList(): List<DefinitionState> {
    return this.map { it.toDefinitionState() }
}

fun Word.toWordState(): WordState {
    return WordState(
        word = word,
        phonetics = phonetics.toPhoneticStateList(),
        meanings = meanings.toMeaningStateList()

    )
}

fun List<Word>.toWordStateList(): List<WordState> {
    return this.map { it.toWordState() }
}

fun SearchWordPresentation.toWordRoom(): SearchRoom {
    return SearchRoom(
        id = id,
        word = word,
        folderId = folderId,
        definition = definition,
        audio = audioUrl
    )
}

fun SearchFolder.toFolderRoomState(): SearchFolderState {
    return SearchFolderState(
        id = id,
        name = name
    )
}

fun SearchFolderWithCount.toSearchFolderWithCountState(): SearchFolderWithCountState {
    return SearchFolderWithCountState(
        folder = folder.toFolderRoomState(),
        wordCount = 0
    )
}

fun List<SearchFolderWithCount>.toSearchFolderWithCountStateList():
        List<SearchFolderWithCountState> {
    return this.map { it.toSearchFolderWithCountState() }
}
