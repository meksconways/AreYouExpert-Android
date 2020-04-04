package com.meksconway.areyouexpert.domain.usecase

import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.common.Decider
import com.meksconway.areyouexpert.common.QuizCategoryResources
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategories
import com.meksconway.areyouexpert.domain.repository.HomeRepository
import com.meksconway.areyouexpert.enums.BannerCategory
import com.meksconway.areyouexpert.enums.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUseCase
@Inject constructor(private val repository: HomeRepository) {

    private var compositeDisposable: CompositeDisposable? = null
    private var homeContent: CombineHomeData = CombineHomeData(
        createBanner(),createTitle(), arrayListOf()
    )

    fun getHomeData(
        source: MutableLiveData<Resource<CombineHomeData>>,
        compositeDisposable: CompositeDisposable
    ) {
        this.compositeDisposable = compositeDisposable
        source.value = Resource.Loading()
        compositeDisposable.add(
            repository.getRemoteCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    fetchLocaleCategories(it,source)
                }
                .doOnError {
                    source.value = Resource.Error(it.message ?: "An error occurred")
                }
                .subscribe()
        )
    }

    private fun createTitle(): TitleModel {
        return TitleModel(title = "Categories")
    }

    private fun fetchLocaleCategories(response: List<QuizCategories>,
                                      source: MutableLiveData<Resource<CombineHomeData>>) {
        compositeDisposable?.add(
            repository.getLocalCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext {
                    if (it.isNullOrEmpty()) {
                        repository.insertCategoryList(response.mapToEntity())
                        homeContent.categoriesModel = response.mapToCategoryModel()
                        source.value = Resource.Success(homeContent)
                    } else {
                        homeContent.categoriesModel = it.mapToCategory()
                        source.value = Resource.Success(homeContent)
                    }
                }
                .doOnError {
                    repository.insertCategoryList(response.mapToEntity())
                    homeContent.categoriesModel = response.mapToCategoryModel()
                    source.value = Resource.Success(homeContent)
                }
                .subscribe()
        )
        

    }

    private fun createBanner(): List<HomeBannerModel> {
        val arr = arrayListOf<HomeBannerModel>()
        arr.add(
            HomeBannerModel(
                bannerId = 0,
                bannerImage = R.drawable.ic_action_fav,
                category = BannerCategory.WELCOME

            )
        )
        return arr
    }
}

fun List<QuizCategories>.mapToEntity(): List<QuizCategoryEntity> {
    return map { it.mapToEntity() }
}

fun List<QuizCategories>.mapToCategoryModel(): List<CategoriesModel> {
    return map { it.mapToEntity().mapToCategoriesModel() }
}

fun List<QuizCategoryEntity>.mapToCategory(): List<CategoriesModel> {
    return map { it.mapToCategoriesModel() }
}

fun QuizCategoryEntity.mapToCategoriesModel(): CategoriesModel {
    return CategoriesModel(id, progress, name, Decider.getCategoryResources(id))
}

fun QuizCategories.mapToEntity(): QuizCategoryEntity {
    return QuizCategoryEntity(id = this.id, name = name, progress = 0)
}



data class CombineHomeData(
    var banner: List<HomeBannerModel>,
    var titleModel: TitleModel,
    var categoriesModel: List<CategoriesModel>
)

data class HomeBannerModel(
    val bannerId: Int,
    @DrawableRes
    val bannerImage: Int,
    val category: BannerCategory
)

data class TitleModel(val title: String)

data class CategoriesModel(
    val id: Int,
    val progress: Int,
    val name: String,
    val resources: QuizCategoryResources
)

enum class ContentItemType {
    BANNER,
    TITLE,
    CATEGORY
}

interface HomeItemType {
    fun getItemType(): ContentItemType
}