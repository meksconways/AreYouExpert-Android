package com.meksconway.areyouexpert.ui.fragment.makesuggestion

import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.MakeSuggestionUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import javax.inject.Inject

interface MakeSuggestionViewModelInput : Input {
    fun sendSuggestion(string: String?)
}

interface MakeSuggestionViewModelOutput : Output {
    val sendData: MutableLiveData<Res<String>>
}

class MakeSuggestionViewModel
@Inject constructor(private val useCase: MakeSuggestionUseCase) :
    BaseViewModel<MakeSuggestionViewModelInput, MakeSuggestionViewModelOutput>(),
    MakeSuggestionViewModelInput, MakeSuggestionViewModelOutput {
    override val input: MakeSuggestionViewModelInput = this
    override val output: MakeSuggestionViewModelOutput = this

    override fun sendSuggestion(string: String?) {
    }

    override val sendData= MutableLiveData<Res<String>>()
}
