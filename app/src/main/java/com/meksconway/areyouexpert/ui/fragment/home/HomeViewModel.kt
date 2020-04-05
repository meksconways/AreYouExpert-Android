package com.meksconway.areyouexpert.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.domain.usecase.HomeUseCase
import com.meksconway.areyouexpert.enums.BannerCategory
import com.meksconway.areyouexpert.enums.Resource
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface HomeViewModelInput : Input {

    fun getHomeContent()
    fun selectCategory(catId: Int)
    fun selectNotifications()
    fun selectProfile()
    fun selectBanner(type: BannerCategory)

}

interface HomeViewModelOutput : Output {

    var homeContentOutput: MediatorLiveData<Resource<HomeContentModel>>

}

class HomeViewModel
@Inject constructor(private val useCase: HomeUseCase) :
    BaseViewModel<HomeViewModelInput, HomeViewModelOutput>(), HomeViewModelInput,
    HomeViewModelOutput {

    override val input: HomeViewModelInput = this
    override val output: HomeViewModelOutput = this
    init {
        getContent(compositeDisposable)
    }

    val _data = MutableLiveData<Res<HomeContentModel>>()

    private fun getContent(compositeDisposable: CompositeDisposable) {
        compositeDisposable.add(
            useCase.getHomeContent(compositeDisposable)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    _data.value = it
                }
                .subscribe()
        )

    }

    //inputs
    override fun getHomeContent() {


    }

    override fun selectCategory(catId: Int) {

    }

    override fun selectNotifications() {

    }

    override fun selectProfile() {

    }

    override fun selectBanner(type: BannerCategory) {

    }

    override var homeContentOutput = MediatorLiveData<Resource<HomeContentModel>>()


}
