package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.data.repository.HomeRepositoryImpl
import com.meksconway.areyouexpert.data.repository.NotificationRepositoryImpl
import com.meksconway.areyouexpert.data.repository.SettingsRepositoryImpl
import com.meksconway.areyouexpert.domain.repository.HomeRepository
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

}