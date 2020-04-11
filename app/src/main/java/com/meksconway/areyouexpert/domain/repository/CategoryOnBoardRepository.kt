package com.meksconway.areyouexpert.domain.repository

import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Observable

interface CategoryOnBoardRepository {

    fun getLocalCategoryEntity(): Observable<List<CategoryProgressEntity>>
    fun insertCategoryEntity(progressEntity: CategoryProgressEntity)

}