package com.example.domain.model.network

data class Word(
    val word: String = "",
    val phonetics: List<Phonetic> = emptyList<Phonetic>(),
    val meanings: List<Meanings> = emptyList<Meanings>()
)