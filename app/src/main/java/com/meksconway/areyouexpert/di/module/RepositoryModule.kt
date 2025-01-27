package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.data.repository.*
import com.meksconway.areyouexpert.domain.repository.*
import com.meksconway.areyouexpert.data.repository.HomeRepositoryImpl
import com.meksconway.areyouexpert.data.repository.MakeSuggestionRepositoryImpl
import com.meksconway.areyouexpert.data.repository.NotificationRepositoryImpl
import com.meksconway.areyouexpert.data.repository.SettingsRepositoryImpl
import com.meksconway.areyouexpert.domain.repository.HomeRepository
import com.meksconway.areyouexpert.domain.repository.MakeSuggestionRepository
import com.meksconway.areyouexpert.domain.repository.NotificationRepository
import com.meksconway.areyouexpert.domain.repository.SettingsRepository
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
    abstract fun bindMakeSuggestionRepository(repository: MakeSuggestionRepositoryImpl): MakeSuggestionRepository

    @Binds
    abstract fun bindCategoryOnBoardRepository(repository: CategoryOnBoardRepositoryImpl): CategoryOnBoardRepository

    @Binds
    abstract fun bindQuizRepository(repository: QuizRepositoryImpl): QuizRepository

}