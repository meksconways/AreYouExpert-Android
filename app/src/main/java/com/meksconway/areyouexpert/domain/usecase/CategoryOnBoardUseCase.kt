package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.domain.repository.CategoryOnBoardRepository
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryOnBoardUseCase @Inject constructor(
    private val repository: CategoryOnBoardRepository
) {

    private val categoryOnBoardContent = arrayListOf<CategoryOnBoardItem>()

    fun getContent(category: CategoryModel): Observable<Res<List<CategoryOnBoardItem>>> {
        categoryOnBoardContent.clear()
        return Observable.create { emitter ->
            emitter.onNext(Res.loading())
            repository.getLocalCategoryEntity()
                .subscribeOn(Schedulers.computation())
                .map {
                    categoryOnBoardContent.add(
                        CategoryOnBoardHeaderModel(-1, category)
                    )
                    categoryOnBoardContent.addAll(it.filter { entity ->
                        entity.categoryName == category.name
                    }.mapToModel(category))
                    emitter.onNext(Res.success(categoryOnBoardContent))
                }
                .doOnError {
                    emitter.onNext(Res.error(it))
                }
                .subscribe()
        }
    }




}

data class CategoryOnBoardHeaderModel(
    val headerId: Int,
    val model: CategoryModel
) : CategoryOnBoardItem {
    override fun getItemType() = CategoryOnBoardItemType.HEADER
    override fun getId() = headerId
}

fun List<CategoryOnBoardHeaderModel>.convertToMap(category: CategoryModel): List<CategoryOnBoardHeaderModel> {
    return this.map {
        CategoryOnBoardHeaderModel(it.headerId, category)
    }
}

fun List<CategoryProgressEntity>.mapToModel(category: CategoryModel): List<CategoryProgressModel> {
    return this.map {
        CategoryProgressModel(it.categoryProgressId, it.progress, category)
    }
}

data class CategoryProgressModel(
    val progressId: Int,
    val progress: Int,
    val content: CategoryModel
) : CategoryOnBoardItem {
    override fun getItemType() = CategoryOnBoardItemType.PROGRESS
    override fun getId() = progressId
    fun getPercent() = "Achievement: ${progress * 10}%"
}

enum class CategoryOnBoardItemType {
    HEADER,
    PROGRESS
}

interface CategoryOnBoardItem {
    fun getItemType(): CategoryOnBoardItemType
    fun getId(): Int
}
