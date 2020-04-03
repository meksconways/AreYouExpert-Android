package com.meksconway.areyouexpert.base

import android.os.Bundle
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<I : Input, O : Output, VM : BaseViewModel<I, O>> :
    DaggerAppCompatActivity() {


    abstract val layRes: Int
    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layRes)
        observeViewModel(viewModel.output)
    }

    abstract fun observeViewModel(output: O)


    fun showWarningDialog() {

    }

    fun showOptionsDialog() {

    }


}
