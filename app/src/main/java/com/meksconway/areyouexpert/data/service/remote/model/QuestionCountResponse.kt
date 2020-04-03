package com.meksconway.areyouexpert.data.service.remote.model

import com.google.gson.annotations.SerializedName

data class QuestionCountResponse(
    @SerializedName("category_id")
    val categoryId: Int?,
    @SerializedName("category_question_count")
    val categoryQuestionCount: QuestionCountResponseCategoryQuestionCount
)

data class QuestionCountResponseCategoryQuestionCount(
    @SerializedName("total_question_count")
    val totalQuestionCount: Int,
    @SerializedName("total_easy_question_count")
    val totalEasyQuestionCount: Int,
    @SerializedName("total_medium_question_count")
    val totalMediumQuestionCount: Int,
    @SerializedName("total_hard_question_count")
    val totalHardQuestionCount: Int
)