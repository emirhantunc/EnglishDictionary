package com.example.data.impl

import com.example.data.mapper.toFolderRoomList
import com.example.data.mapper.toWordList
import com.example.data.mapper.toWordModel
import com.example.data.network.service.DictionaryApi
import com.example.data.room.dao.DictionaryDao
import com.example.domain.model.network.Word
import com.example.domain.model.room.FolderRoom
import com.example.domain.model.room.WordRoom
import com.example.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DictionaryRepositoryImpl @Inject constructor(
    private val api: DictionaryApi,
    private val dao: DictionaryDao
) : DictionaryRepository {
    override suspend fun getWordMeaning(word: String): List<Word> {
        val response = api.getWordMeaning(word = word)
        if (response.isSuccessful) {
            return response.body()?.toWordList() ?: emptyList()
        }
        return throw Exception("Error Dictionary: ${response.code()} - ${response.message()}")
    }

    override suspend fun insertMeaning(wordRoom: WordRoom) {
        dao.insertMeaning(wordModel = wordRoom.toWordModel())
    }

    override suspend fun getAllFolders(): Flow<List<FolderRoom>> {
        return dao.getAllFolders().map { list ->
            list.toFolderRoomList()
        }
    }
}