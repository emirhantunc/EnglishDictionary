package com.example.presentation.model.network

data class WordState(
    val word: String = "",
    val phonetics: List<PhoneticState> = emptyList(),
    val meanings: List<MeaningState> = emptyList()
)
