package com.example.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folder")
data class FolderModel(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String
)
