package com.meksconway.areyouexpert.di.module

import com.meksconway.areyouexpert.ui.fragment.categoryonbard.CategoryOnBoardFragment
import com.meksconway.areyouexpert.ui.fragment.home.HomeFragment
import com.meksconway.areyouexpert.ui.fragment.notification.NotificationFragment
import com.meksconway.areyouexpert.ui.fragment.settings.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeNotificationFragment(): NotificationFragment

    @ContributesAndroidInjector
    abstract fun contributeSettingsFragment(): SettingsFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoryOnBoardFragment(): CategoryOnBoardFragment

}