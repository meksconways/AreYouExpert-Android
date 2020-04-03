package com.meksconway.areyouexpert.data.remote

import com.meksconway.areyouexpert.data.remote.model.QuestionsResponse
import com.meksconway.areyouexpert.data.remote.model.QuizCategoryResponse
import com.meksconway.areyouexpert.data.remote.model.TokenGenerateResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface TriviaApiService {

    @GET(
        "api.php?" +
                "amount={amount}" +
                "&category={category}" +
                "&difficulty={difficulty}" +
                "&type={type}" +
                "&token={token}"
    )
    fun getQuestions(
        @Path("amount") amount: String,
        @Path("category") category: String,
        @Path("difficulty") difficulty: String,
        @Path("type") type: String,
        @Path("token") token: String
    ): Single<QuestionsResponse>

    @GET("api_token.php?command=request")
    fun generateToken(): Single<TokenGenerateResponse>

    @GET("api_category.php")
    fun getCategories(): Single<QuizCategoryResponse>

}