package com.example.data.network.model

import com.google.gson.annotations.SerializedName

data class PhoneticResponse(
    @SerializedName("text") val phonetic: String = "",
    @SerializedName("audio") val audio: String = ""
)

