package com.meksconway.areyouexpert.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.meksconway.areyouexpert.di.ViewModelKey
import com.meksconway.areyouexpert.ui.activity.main.MainAcitivityViewModel
import com.meksconway.areyouexpert.ui.adapter.SettingsAdapter
import com.meksconway.areyouexpert.ui.fragment.categoryonbard.CategoryOnBoardViewModel
import com.meksconway.areyouexpert.ui.fragment.home.HomeViewModel
import com.meksconway.areyouexpert.ui.fragment.makesuggestion.MakeSuggestionViewModel
import com.meksconway.areyouexpert.ui.fragment.notification.NotificationViewModel
import com.meksconway.areyouexpert.ui.fragment.quiz.QuizViewModel
import com.meksconway.areyouexpert.ui.fragment.settings.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(vm: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NotificationViewModel::class)
    abstract fun bindNotificationViewModel(vm: NotificationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    abstract fun bindSettingsViewModel(vm: SettingsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainAcitivityViewModel::class)
    abstract fun bindMainViewModel(vm: MainAcitivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryOnBoardViewModel::class)
    abstract fun bindCategoryOnBoardViewModel(vm: CategoryOnBoardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(QuizViewModel::class)
    abstract fun bindQuizViewModel(vm: QuizViewModel): ViewModel



    @Binds
    @IntoMap
    @ViewModelKey(MakeSuggestionViewModel::class)
    abstract fun bindMakeSuggestionViewModel(vm: MakeSuggestionViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory


}
