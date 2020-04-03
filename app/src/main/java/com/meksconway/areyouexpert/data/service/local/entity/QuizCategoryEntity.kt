package com.meksconway.areyouexpert.data.service.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meksconway.areyouexpert.common.QuizCategoryGradientType

@Entity(tableName = "quiz_category")
data class QuizCategoryEntity(
    @PrimaryKey
    val id: Int,
    val progress: Int,
    val name: String,
    val resId: Int,
    val gradientType: QuizCategoryGradientType
)