package com.example.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.room.dao.DictionaryDao
import com.example.data.room.model.FolderModel
import com.example.data.room.model.WordModel

@Database(
    entities = [
        FolderModel::class,
        WordModel::class
    ],

    version = 3,
    exportSchema = false
)
abstract class DictionaryDatabase : RoomDatabase() {
    abstract fun wordDao(): DictionaryDao
}