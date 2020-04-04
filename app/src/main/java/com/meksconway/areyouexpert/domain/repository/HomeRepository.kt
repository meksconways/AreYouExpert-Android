package com.meksconway.areyouexpert.domain.repository

import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategories
import io.reactivex.Observable


interface HomeRepository {

    fun getRemoteCategories(): Observable<List<QuizCategories>>
    fun getLocalCategories(): Observable<List<QuizCategoryEntity>>
    fun insertCategory(categoryEntity: QuizCategoryEntity)
    fun insertCategoryList(categoryEntity: List<QuizCategoryEntity>)

}





