package com.meksconway.areyouexpert.di

import android.app.Application
import com.meksconway.areyouexpert.base.App
import com.meksconway.areyouexpert.di.module.*
import com.meksconway.areyouexpert.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        AppModule::class,
        RoomModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        FragmentBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}