package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.datasource.local.SharedPreferencesLocalDataSource
import com.meksconway.areyouexpert.data.datasource.remote.TriviaRemoteDataSource
import com.meksconway.areyouexpert.datasource.local.RoomLocalDataSourceImpl
import com.meksconway.areyouexpert.datasource.local.SharedPreferencesLocalDataSourceImpl
import com.meksconway.areyouexpert.datasource.remote.TriviaRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {

    @Binds
    abstract fun bindTriviaRemoteDataSource(source: TriviaRemoteDataSourceImpl): TriviaRemoteDataSource

    @Binds
    abstract fun bindSharedPreferencesLocalDataSource(source: SharedPreferencesLocalDataSourceImpl): SharedPreferencesLocalDataSource

    @Binds
    abstract fun bindRoomLocalDataSource(source: RoomLocalDataSourceImpl): RoomLocalDataSource
}