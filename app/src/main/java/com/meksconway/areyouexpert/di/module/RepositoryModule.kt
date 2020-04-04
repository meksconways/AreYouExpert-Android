package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.data.repository.HomeRepositoryImpl
import com.meksconway.areyouexpert.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindHomeRepository(repository: HomeRepositoryImpl): HomeRepository

}