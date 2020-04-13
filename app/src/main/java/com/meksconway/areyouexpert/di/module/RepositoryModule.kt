package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.data.repository.*
import com.meksconway.areyouexpert.domain.repository.*
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindNotificationRepository(repository: NotificationRepositoryImpl): NotificationRepository

    @Binds
    abstract fun bindSettingsRepository(repository: SettingsRepositoryImpl): SettingsRepository

    @Binds
    abstract fun bindCategoryOnBoardRepository(repository: CategoryOnBoardRepositoryImpl): CategoryOnBoardRepository

    @Binds
    abstract fun bindQuizRepository(repository: QuizRepositoryImpl): QuizRepository

}