package com.example.data.room.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "word", foreignKeys = [
        ForeignKey(
            entity = FolderModel::class,
            parentColumns = ["id"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("folderId")]
)
data class WordModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val word: String,
    val folderId: Int,
    val definition: String,
    val audio: String
)
