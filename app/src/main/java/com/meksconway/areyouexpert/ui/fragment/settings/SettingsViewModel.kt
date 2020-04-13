package com.meksconway.areyouexpert.ui.fragment.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.SettingsModel
import com.meksconway.areyouexpert.domain.usecase.SettingsUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import com.meksconway.areyouexpert.viewmodel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.lang.reflect.Constructor
import javax.inject.Inject

interface SettingsViewModelInput : Input {

    fun getSettings()
    fun resetProgress()
    fun clickSettings(id: Int)
}

interface SettingsViewModelOutput : Output {
    val settingsOutput: LiveData<Res<List<SettingsModel>>>
    val resetProgressOutput: LiveData<Res<Boolean>>
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
            .subscribe { _settingsOutput.value = Res.success(it) }
            .addTo(compositeDisposable)
    }

    override fun resetProgress() {
        useCase.resetProgress()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _resetProgressOutput.value = it
            }
            .addTo(compositeDisposable)
    }

    override fun clickSettings(id: Int) {

    }

    //outputs
    private val _settingsOutput = MutableLiveData<Res<List<SettingsModel>>>()
    override val settingsOutput: LiveData<Res<List<SettingsModel>>>
        get() = _settingsOutput

    private val _resetProgressOutput =  MutableLiveData<Res<Boolean>>()
    override val resetProgressOutput: LiveData<Res<Boolean>>
        get() = _resetProgressOutput
}