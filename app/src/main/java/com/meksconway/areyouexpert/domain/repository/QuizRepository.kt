package com.meksconway.areyouexpert.domain.repository

import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponseResults
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Completable
import io.reactivex.Observable

interface QuizRepository {

    /*
    * @Path("amount") amount: String?,
        @Path("category") category: String?,
        @Path("difficulty") difficulty: String?,
        @Path("type") type: String?,
        @Path("token") token: String?
    * */

    fun getCategoryQuestions(categoryId: Int): Observable<MutableList<QuestionsResponseResults>?>
    fun insertCategoryProgressEntity(progressEntity: CategoryProgressEntity): Completable
    fun updateQuizCategoryProgress(categoryId: Int): Completable

}