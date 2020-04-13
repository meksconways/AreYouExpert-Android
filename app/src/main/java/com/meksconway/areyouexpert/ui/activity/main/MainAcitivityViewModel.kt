package com.meksconway.areyouexpert.ui.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.util.ToolbarConfigration
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import javax.inject.Inject


interface MainInput : Input {

    fun setToolbarConfig(config: ToolbarConfigration)

}

interface MainOutput : Output {

    val toolbarConfigOutput: LiveData<ToolbarConfigration>

}

class MainAcitivityViewModel @Inject constructor() : BaseViewModel<MainInput, MainOutput>(),
    MainInput, MainOutput {

    override val input: MainInput
        get() = this
    override val output: MainOutput
        get() = this

    override fun setToolbarConfig(config: ToolbarConfigration) {
        _toolbarConfigOutput.value = config
    }

    private val _toolbarConfigOutput = MutableLiveData<ToolbarConfigration>()
    override val toolbarConfigOutput: LiveData<ToolbarConfigration>
        get() = _toolbarConfigOutput


}