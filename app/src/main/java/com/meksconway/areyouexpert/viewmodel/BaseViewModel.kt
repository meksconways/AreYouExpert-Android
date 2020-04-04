package com.meksconway.areyouexpert.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

interface Input {}
interface Output {}

interface IBaseViewModel<I : Input, O : Output> {

    val input: I
    val output: O
}

abstract class BaseViewModel<I : Input, O : Output> : ViewModel(),
    IBaseViewModel<I, O> {

    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}

