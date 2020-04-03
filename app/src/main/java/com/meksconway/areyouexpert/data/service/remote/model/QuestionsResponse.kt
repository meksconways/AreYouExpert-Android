package com.meksconway.areyouexpert.data.service.remote.model

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("response_code")
    val responseCode: Int?,
    val results: List<QuestionsResponseResults>?
)

data class QuestionsResponseResults(
    val category: String?,
    val type: String?,
    val difficulty: String?,
    val question: String?,
    @SerializedName("correct_answer")
    val correctAnswer: String?,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>?
)