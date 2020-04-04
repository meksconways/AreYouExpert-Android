package com.meksconway.areyouexpert.ui.fragment.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {


    override val layRes: Int
        get() = R.layout.home_fragment

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {

        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()


    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }


}
