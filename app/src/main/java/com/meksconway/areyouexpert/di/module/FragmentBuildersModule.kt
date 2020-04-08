package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.ui.fragment.home.HomeFragment
import com.meksconway.areyouexpert.ui.fragment.notification.NotificationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
    @ContributesAndroidInjector
    abstract fun contributerNotificationFragment(): NotificationFragment

}