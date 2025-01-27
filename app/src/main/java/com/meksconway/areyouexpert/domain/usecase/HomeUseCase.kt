package com.meksconway.areyouexpert.domain.usecase

import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.common.Decider
import com.meksconway.areyouexpert.common.QuizCategoryResources
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategories
import com.meksconway.areyouexpert.domain.repository.HomeRepository
import com.meksconway.areyouexpert.enums.BannerCategory
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeUseCase
@Inject constructor(private val repository: HomeRepository) {

    private var homeContent = HomeContentModel(content = arrayListOf())

    fun getHomeContent(): Observable<Res<HomeContentModel>> {
        homeContent.content.clear()
        return Observable.create<Res<HomeContentModel>> { emitter ->
            emitter.onNext(Res.loading())
            homeContent.content.add(
                HomeBannerModel(
                    banner = createBanner()
                )
            )
            homeContent.content.add(createTitle())

            repository.getLocalCategories()
                .map {
                    if (it.isNullOrEmpty()) {
                        repository.getRemoteCategories()
                            .subscribeOn(Schedulers.io())
                            .subscribe { cat ->
                                cat?.let { quiz ->
                                    Completable.fromAction {
                                        repository.insertCategoryList(quiz.mapToEntity())
                                    }.subscribeOn(Schedulers.io())
                                        .subscribe {
                                            homeContent.content.addAll(quiz.mapToCategoryModel())
                                            emitter.onNext(Res.success(homeContent))
                                        }
                                } ?: kotlin.run {
                                    emitter.onNext(Res.error(Throwable("asd")))
                                }

                            }
                    } else {
                        homeContent.content.addAll(it.mapToCategory())
                        emitter.onNext(Res.success(homeContent))
                    }
                }
                .subscribe()
        }

    }


    private fun createTitle(): TitleModel {
        return TitleModel(title = "Categories")
    }


    private fun createBanner(): List<HomeBannerListModel> {
        val arr = arrayListOf<HomeBannerListModel>()
        arr.add(
            HomeBannerListModel(
                bannerId = 0,
                bannerImage = R.drawable.welcome_image,
                bannerGradient = R.drawable.gradient_flare,
                bannerTitle = "Welcome To App",
                category = BannerCategory.WELCOME

            )
        )
        arr.add(
            HomeBannerListModel(
                bannerId = 1,
                bannerImage = R.drawable.welcome_image,
                bannerGradient = R.drawable.gredient_yoda,
                bannerTitle = "Stay Home And Solve Questions",
                category = BannerCategory.WELCOME

            )
        )
        return arr
    }
}

fun List<QuizCategories>.mapToEntity(): List<QuizCategoryEntity> {
    return map { it.mapToEntity() }
}

fun List<QuizCategories>.mapToCategoryModel(): List<CategoryModel> {
    return map { it.mapToEntity().mapToCategoriesModel() }
}

fun List<QuizCategoryEntity>.mapToCategory(): List<CategoryModel> {
    return map { it.mapToCategoriesModel() }
}

fun QuizCategoryEntity.mapToCategoriesModel(): CategoryModel {
    return CategoryModel(Id, progress, name, Decider.getCategoryResources(Id))
}

fun QuizCategories.mapToEntity(): QuizCategoryEntity {
    return QuizCategoryEntity(Id = this.id, name = name, progress = 0)
}


data class HomeContentModel(
    var content: MutableList<HomeItemType>
)


//***************** HOME BANNER ****************************
data class HomeBannerModel(
    val banner: List<HomeBannerListModel>
) : HomeItemType {
    override fun getItemType() = ContentItemType.BANNER
    override fun getContentId() = 710
}

data class HomeBannerListModel(
    val bannerId: Int,
    @DrawableRes
    val bannerImage: Int,
    @DrawableRes
    val bannerGradient: Int,
    val bannerTitle: String,
    val category: BannerCategory
)
//***************** HOME BANNER ****************************

//***************** TITLE **********************************
data class TitleModel(val title: String) : HomeItemType {
    override fun getItemType() = ContentItemType.TITLE
    override fun getContentId() = title.length.hashCode()
}
//***************** TITLE **********************************

@Parcelize
data class CategoryModel(
    val id: Int,
    val progress: Int,
    val name: String,
    val resources: QuizCategoryResources

): Parcelable, HomeItemType {
    override fun getItemType() = ContentItemType.CATEGORY
    override fun getContentId() = id
}

enum class ContentItemType {
    BANNER,
    TITLE,
    CATEGORY
}

interface HomeItemType {
    fun getItemType(): ContentItemType
    fun getContentId(): Int
}