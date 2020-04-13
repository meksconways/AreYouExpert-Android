package com.meksconway.areyouexpert.data.service.remote.model

import com.google.gson.annotations.SerializedName
import com.meksconway.areyouexpert.domain.usecase.Answer

data class QuestionsResponse(
    @SerializedName("response_code")
    val responseCode: Int?,
    var results: MutableList<QuestionsResponseResults>?
)

data class QuestionsResponseResults(
    val category: String,
    val type: String,
    val difficulty: String,
    var totalQuestion: Int,
    var questionId: Int,
    val question: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: MutableList<String>,
    var answers: MutableList<Answer>
) {
    fun getQuestionTitleText() = "Question ${questionId + 1}/$totalQuestion"
}