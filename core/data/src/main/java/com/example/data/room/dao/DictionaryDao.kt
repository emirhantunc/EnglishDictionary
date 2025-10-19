package com.example.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.data.room.model.FolderModel
import com.example.data.room.model.FolderModelWithWordCount
import com.example.data.room.model.WordModel
import kotlinx.coroutines.flow.Flow


@Dao
interface DictionaryDao {

    @Query("""
        SELECT 
            folder.*, 
            COUNT(word.id) as wordCount 
        FROM 
            folder 
        LEFT JOIN 
            word ON folder.id = word.folderId 
        GROUP BY 
            folder.id
    """)
    fun getAllFolders(): Flow<List<FolderModelWithWordCount>>

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