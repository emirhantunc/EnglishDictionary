package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class DefinitionResponse(
    @SerializedName("definition") val definition: String = "",
    @SerializedName("example") val example: String = "",
)
