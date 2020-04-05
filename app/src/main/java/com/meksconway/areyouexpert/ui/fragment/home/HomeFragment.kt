package com.meksconway.areyouexpert.ui.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.CombineHomeData
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.enums.Resource

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    override val layRes: Int
        get() = R.layout.home_fragment

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            checkHomeContentOutput(it)
        })
        viewModel?.input?.getHomeContent()
    }

    private fun checkHomeContentOutput(resource: Resource<HomeContentModel>) {
        when (resource) {
            is Resource.Success -> {
                // set Adapter
            }

            is Resource.Error -> {
                //show errors
            }

            is Resource.Loading -> {
                //show progress
            }
        }
    }

    override fun viewDidLoad() {
        super.viewDidLoad()


    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }


}
