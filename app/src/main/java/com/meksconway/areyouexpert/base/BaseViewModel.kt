package com.meksconway.areyouexpert.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

interface Input
interface Output {
    val networkError: MutableLiveData<Error> //Single olcak
}

data class Error(val message: String, val code: Int)

interface IBaseViewModel<I : Input, O : Output> {

    val input: I
    val output: O
}

abstract class BaseViewModel<I : Input, O : Output> : ViewModel(), IBaseViewModel<I, O> {

    val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

}

