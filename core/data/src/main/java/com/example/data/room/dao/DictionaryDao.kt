package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.example.data.room.model.FolderModel
import com.example.data.room.model.WordModel
import kotlinx.coroutines.flow.Flow


@Dao
interface DictionaryDao {

    @Query("SELECT * FROM folder")
    fun getAllFolders(): Flow<List<FolderModel>>

    @Query("SELECT * FROM folder WHERE id=:id")
    suspend fun getFolderById(id: Int): FolderModel

    @Query("SELECT * FROM word WHERE folderId =:folderId")
    suspend fun getWords(folderId: Int): List<WordModel>

    @Upsert
    suspend fun insertFolder(list: FolderModel)

    @Delete
    suspend fun deleteFolder(list: FolderModel)


    @Upsert
    suspend fun insertMeaning(wordModel: WordModel)

}