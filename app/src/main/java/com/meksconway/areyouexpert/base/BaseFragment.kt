package com.meksconway.areyouexpert.base

import android.content.Context
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

class BaseFragment : DaggerFragment() {


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

}