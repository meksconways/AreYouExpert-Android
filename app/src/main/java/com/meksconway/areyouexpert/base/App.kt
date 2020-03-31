package com.meksconway.areyouexpert.base

import com.meksconway.areyouexpert.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication


class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent
            .builder()
            .application(this)
            .build()
    }



}