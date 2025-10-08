package com.example.data.room.module

import android.content.Context
import androidx.room.Room
import com.example.data.room.dao.DictionaryDao
import com.example.data.room.database.DictionaryDatabase
import com.example.data.room.migration.MIGRATION_2_3
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): DictionaryDatabase {
        return Room.databaseBuilder(
            context,
            DictionaryDatabase::class.java,
            "word_database"
        ).addMigrations(MIGRATION_2_3).build()
    }

    @Provides
    @Singleton
    fun provideWordDao(database: DictionaryDatabase): DictionaryDao = database.wordDao()

}