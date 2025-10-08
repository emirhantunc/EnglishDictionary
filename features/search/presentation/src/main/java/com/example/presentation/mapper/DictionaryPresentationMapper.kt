package com.example.presentation.mapper

import com.example.domain.model.network.Definitions
import com.example.domain.model.network.Meanings
import com.example.domain.model.network.Phonetic
import com.example.domain.model.network.Word
import com.example.domain.model.room.FolderRoom
import com.example.domain.model.room.WordRoom
import com.example.presentation.model.network.DefinitionState
import com.example.presentation.model.network.MeaningState
import com.example.presentation.model.network.PhoneticState
import com.example.presentation.model.network.WordState
import com.example.presentation.model.room.FolderRoomState
import com.example.presentation.model.room.WordRoomPresentation

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

fun WordRoomPresentation.toWordRoom(): WordRoom {
    return WordRoom(
        id = id,
        word = word,
        folderId = folderId,
        definition = definition
    )
}

fun FolderRoom.toFolderRoomState(): FolderRoomState {
    return FolderRoomState(
        id = id,
        name = name
    )
}

fun List<FolderRoom>.toFolderRoomStateList(): List<FolderRoomState> {
    return this.map { it.toFolderRoomState() }
}
