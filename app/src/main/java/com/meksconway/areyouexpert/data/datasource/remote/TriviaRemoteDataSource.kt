package com.meksconway.areyouexpert.data.datasource.remote

import com.meksconway.areyouexpert.data.service.remote.model.QuestionCountResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategoryResponse
import com.meksconway.areyouexpert.data.service.remote.model.TokenGenerateResponse
import io.reactivex.Observable

interface TriviaRemoteDataSource {

    fun getQuestions(
        amount: String?,
        category: String?,
        difficulty: String?,
        type: String?,
        token: String?
    ): Observable<QuestionsResponse>

    fun generateToken(): Observable<TokenGenerateResponse>

    fun getCategories(): Observable<QuizCategoryResponse>

    fun getCategoryQuestionCount(categoryId: String): Observable<QuestionCountResponse>

}