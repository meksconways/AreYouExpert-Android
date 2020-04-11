package com.meksconway.areyouexpert.ui.fragment.categoryonbard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.domain.usecase.CategoryModel
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItem
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

interface CategoryOnBoardInput : Input {
    fun getContent(category: CategoryModel)
}

interface CategoryOnBoardOutput : Output {
    val contentOutput: LiveData<Res<List<CategoryOnBoardItem>>>
}

class CategoryOnBoardViewModel @Inject constructor(
    private val useCase: CategoryOnBoardUseCase
) : BaseViewModel<CategoryOnBoardInput, CategoryOnBoardOutput>(),
    CategoryOnBoardInput, CategoryOnBoardOutput {

    override val input: CategoryOnBoardInput
        get() = this
    override val output: CategoryOnBoardOutput
        get() = this


    override fun getContent(category: CategoryModel) {
        useCase.getContent(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _contentOutput.value = it
            }
            .addTo(compositeDisposable)
    }


    private val _contentOutput = MutableLiveData<Res<List<CategoryOnBoardItem>>>()
    override val contentOutput: LiveData<Res<List<CategoryOnBoardItem>>>
        get() = _contentOutput


}
