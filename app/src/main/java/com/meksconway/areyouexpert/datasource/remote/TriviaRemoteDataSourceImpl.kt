package com.meksconway.areyouexpert.datasource.remote

import com.meksconway.areyouexpert.data.datasource.remote.TriviaRemoteDataSource
import com.meksconway.areyouexpert.data.service.remote.TriviaApiService
import com.meksconway.areyouexpert.data.service.remote.model.QuestionCountResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategoryResponse
import com.meksconway.areyouexpert.data.service.remote.model.TokenGenerateResponse
import io.reactivex.Observable
import javax.inject.Inject

class TriviaRemoteDataSourceImpl
@Inject
constructor(private val triviaApiService: TriviaApiService) :
    TriviaRemoteDataSource {

    override fun getQuestions(
        amount: String?,
        categoryId: Int,
        difficulty: String?,
        type: String?,
        token: String?
    ): Observable<QuestionsResponse> {
        return triviaApiService.getQuestions(categoryId)
            .toObservable()
    }

    override fun generateToken(): Observable<TokenGenerateResponse> {
        return triviaApiService.generateToken().toObservable()
    }

    override fun getCategories(): Observable<QuizCategoryResponse> {
        return triviaApiService.getCategories().toObservable()
    }

    override fun getCategoryQuestionCount(categoryId: String): Observable<QuestionCountResponse> {
        return triviaApiService.getCategoryQuestionCount(categoryId).toObservable()
    }


}