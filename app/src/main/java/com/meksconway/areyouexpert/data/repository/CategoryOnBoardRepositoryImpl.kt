package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.domain.repository.CategoryOnBoardRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CategoryOnBoardRepositoryImpl
@Inject constructor(private val localSource: RoomLocalDataSource) : CategoryOnBoardRepository {

    override fun getLocalCategoryEntity(): Observable<List<CategoryProgressEntity>> {
        return localSource.getCategoryProgressEntity()
            .subscribeOn(Schedulers.io())

    }

    override fun insertCategoryEntity(progressEntity: CategoryProgressEntity) {
        localSource.insertProgressEntity(progressEntity)
    }
}