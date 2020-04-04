package com.meksconway.areyouexpert.domain.repository

import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategories
import io.reactivex.Observable


interface HomeRepository {

    fun getCategories(): Observable<List<QuizCategories>>
    fun getLocalCategories(response: List<QuizCategories>?): Observable<List<QuizCategoryEntity>>
    fun insertCategory(categoryEntity: QuizCategoryEntity)

}





