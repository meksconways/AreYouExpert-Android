package com.meksconway.areyouexpert.data.triviamodel

import com.google.gson.annotations.SerializedName

data class QuizCategoryResponse(
    @SerializedName("trivia_categories")
    val categories: List<QuizCategories>?
)

data class QuizCategories(
    val id: Int?,
    val name: String?
)