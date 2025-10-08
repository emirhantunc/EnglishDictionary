package com.example.presentation.model.network

data class MeaningState(
    val definitions: List<DefinitionState> = emptyList(),
    val partOfSpeech: String = ""
)
