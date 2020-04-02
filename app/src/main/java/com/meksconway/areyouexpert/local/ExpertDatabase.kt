package com.meksconway.areyouexpert.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.meksconway.areyouexpert.local.entity.QuizCategoryEntity

@Database(
    entities = [QuizCategoryEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ExpertDatabase : RoomDatabase() {

    abstract fun getDao(): DaoService

    companion object {
        const val DATABASE_NAME = "ExpertDatabase"
    }

}