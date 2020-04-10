package com.meksconway.areyouexpert.ui.fragment.settings

import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.SettingsModel
import com.meksconway.areyouexpert.domain.usecase.SettingsUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

interface SettingsViewModelInput : Input {

    fun getSettings()
    fun clickSettings(id: Int)
}

interface SettingsViewModelOutput : Output {
    val settingsOutput: MutableLiveData<Res<List<SettingsModel>>>
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

    override fun getSettings() {
        useCase.getSettingsList()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { settingsOutput.value = Res.success(it) }
            .addTo(compositeDisposable)
    }

    override fun clickSettings(id: Int) {

    }

    override val settingsOutput = MutableLiveData<Res<List<SettingsModel>>>()
}