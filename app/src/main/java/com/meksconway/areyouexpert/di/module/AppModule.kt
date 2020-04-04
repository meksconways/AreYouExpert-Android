package com.meksconway.areyouexpert.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideSPHelper(context: Context): SharedPreferences {
        return context.getSharedPreferences("areyouexpertSP", Context.MODE_PRIVATE)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

}