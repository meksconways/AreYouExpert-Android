package com.meksconway.areyouexpert.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meksconway.areyouexpert.data.QuizCategoryGradientType

@Entity(tableName = "quiz_category")
data class QuizCategoryEntity(
    @PrimaryKey
    val id: Int,
    val progress: Int,
    val name: String,
    val resId: Int,
    val gradientType: QuizCategoryGradientType
)