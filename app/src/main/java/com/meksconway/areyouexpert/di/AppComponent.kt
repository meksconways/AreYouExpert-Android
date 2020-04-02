package com.meksconway.areyouexpert.di

import android.app.Application
import com.meksconway.areyouexpert.base.App
import com.meksconway.areyouexpert.di.module.ActivityBuildersModule
import com.meksconway.areyouexpert.di.module.AppModule
import com.meksconway.areyouexpert.di.module.NetworkModule
import com.meksconway.areyouexpert.viewmodel.ViewModelFactoryModule
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
        ViewModelFactoryModule::class,
        NetworkModule::class,
        AppModule::class
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