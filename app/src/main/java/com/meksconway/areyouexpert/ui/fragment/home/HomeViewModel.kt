package com.meksconway.areyouexpert.ui.fragment.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.domain.usecase.HomeUseCase
import com.meksconway.areyouexpert.enums.BannerCategory
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import com.meksconway.areyouexpert.viewmodel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

interface HomeViewModelInput : Input {

    fun getHomeContent()
    fun selectCategory(catId: Int)
    fun selectNotifications()
    fun selectProfile()
    fun selectBanner(type: BannerCategory)

}

interface HomeViewModelOutput : Output {

    val homeContentOutput: MutableLiveData<Res<HomeContentModel>>

}

class HomeViewModel
@Inject constructor(private val useCase: HomeUseCase) :
    BaseViewModel<HomeViewModelInput, HomeViewModelOutput>(), HomeViewModelInput,
    HomeViewModelOutput {

    override val input: HomeViewModelInput = this
    override val output: HomeViewModelOutput = this

    init {
        getHomeContent()
    }

    //inputs
    override fun getHomeContent() {
        useCase.getHomeContent()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("***value",it.toString())
                    homeContentOutput.value = it
                },
                {
                    homeContentOutput.value = Res.error(it)
                }
            )
            .addTo(compositeDisposable)
    }

    override fun selectCategory(catId: Int) {

    }

    override fun selectNotifications() {

    }

    override fun selectProfile() {

    }

    override fun selectBanner(type: BannerCategory) {

    }

    override val homeContentOutput = MutableLiveData<Res<HomeContentModel>>()


}
