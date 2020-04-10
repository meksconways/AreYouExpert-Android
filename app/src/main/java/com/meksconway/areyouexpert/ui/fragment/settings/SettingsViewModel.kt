package com.meksconway.areyouexpert.ui.fragment.settings

import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.SettingsListModel
import com.meksconway.areyouexpert.domain.usecase.SettingsUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.toObservable
import javax.inject.Inject

interface SettingsViewModelInput : Input {

    fun getSettings()
    fun clickSettings()
}

interface SettingsViewModelOutput : Output {
    val settingsOutput: MutableLiveData<Res<SettingsListModel>>
}

class SettingsViewModel
@Inject constructor(private val useCase: SettingsUseCase) :
    BaseViewModel<SettingsViewModelInput, SettingsViewModelOutput>(), SettingsViewModelInput,
    SettingsViewModelOutput {
    override val input: SettingsViewModelInput = this
    override val output: SettingsViewModelOutput = this

    init {
        getSettings()
    }

    val _data = MutableLiveData<Res<SettingsViewModel>>()

    override fun getSettings() {
        useCase.getSettingsList()
    }

    override fun clickSettings() {


    }

    override val settingsOutput = MutableLiveData<Res<SettingsListModel>>()
}