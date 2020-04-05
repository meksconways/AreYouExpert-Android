package com.meksconway.areyouexpert.ui.fragment.home

import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.CombineHomeData
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.domain.usecase.HomeUseCase
import com.meksconway.areyouexpert.enums.BannerCategory
import com.meksconway.areyouexpert.enums.Resource
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import javax.inject.Inject

interface HomeViewModelInput : Input {

    fun getHomeContent()
    fun selectCategory(catId: Int)
    fun selectNotifications()
    fun selectProfile()
    fun selectBanner(type: BannerCategory)

}

interface HomeViewModelOutput : Output {

    val homeContentOutput: MutableLiveData<Resource<HomeContentModel>>

}


class HomeViewModel
@Inject constructor(private val useCase: HomeUseCase) :
    BaseViewModel<HomeViewModelInput, HomeViewModelOutput>(), HomeViewModelInput,
    HomeViewModelOutput {

    override val input: HomeViewModelInput = this
    override val output: HomeViewModelOutput = this

    //inputs
    override fun getHomeContent() {
        useCase.getHomeData(
            source = homeContentOutput,
            compositeDisposable = compositeDisposable
        )
    }

    override fun selectCategory(catId: Int) {

    }

    override fun selectNotifications() {

    }

    override fun selectProfile() {

    }

    override fun selectBanner(type: BannerCategory) {

    }

    //outputs
    override val homeContentOutput = MutableLiveData<Resource<HomeContentModel>>()



}
