package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class WordResponse(
    @SerializedName("word") val word: String = "",
    @SerializedName("phonetics") val phonetics: List<PhoneticResponse>? = emptyList<PhoneticResponse>(),
    @SerializedName("meanings") val meanings: List<MeaningsResponse>? = emptyList<MeaningsResponse>()
)

