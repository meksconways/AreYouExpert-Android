package com.meksconway.areyouexpert.di.module

import android.content.Context
import androidx.room.Room
import com.meksconway.areyouexpert.data.local.ExpertDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): ExpertDatabase {
        return Room.databaseBuilder(
            context,
            ExpertDatabase::class.java,
            ExpertDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideDaoService(database: ExpertDatabase) = database.getDao()

}