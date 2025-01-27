package com.meksconway.areyouexpert.data.service.remote

import com.meksconway.areyouexpert.data.service.remote.model.QuestionCountResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponse
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategoryResponse
import com.meksconway.areyouexpert.data.service.remote.model.TokenGenerateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TriviaApiService {

    @GET(
        "api.php?" +
                "amount={amount}" +
                "&category={category}" +
                "&difficulty={difficulty}" +
                "&type={type}" +
                "&token={token}"
    )
    fun getQuestionss(
        @Path("amount") amount: String?,
        @Path("category") category: String?,
        @Path("difficulty") difficulty: String?,
        @Path("type") type: String?,
        @Path("token") token: String?
    ): Single<QuestionsResponse>

    @GET(
        "api.php?amount=10"
    )
    fun getQuestions(
        @Query("category") category: Int
        ): Single<QuestionsResponse>

    @GET("api_token.php?command=request")
    fun generateToken(): Single<TokenGenerateResponse>

    @GET("api_category.php")
    fun getCategories(): Single<QuizCategoryResponse>

    @GET("api_count.php?category={category}")
    fun getCategoryQuestionCount(
        @Path("category") category: String
    ): Single<QuestionCountResponse>

}