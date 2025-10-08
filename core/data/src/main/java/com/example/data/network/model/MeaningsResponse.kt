package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class MeaningsResponse(
    @SerializedName("definitions") val definitionResponse: List<DefinitionResponse> = emptyList<DefinitionResponse>(),
    @SerializedName("partOfSpeech") val partOfSpeech: String
)
