package com.example.data.network.service

import com.example.data.network.model.WordResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

    @GET("api/v2/entries/en/{word}")
    suspend fun getWordMeaning(@Path("word")word: String): Response<List<WordResponse>>


}