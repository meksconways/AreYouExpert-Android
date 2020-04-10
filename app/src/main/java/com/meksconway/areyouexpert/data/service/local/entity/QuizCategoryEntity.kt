package com.meksconway.areyouexpert.data.service.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_category")
data class QuizCategoryEntity(
    @PrimaryKey
    val Id: Int,
    val progress: Int = 0,
    val name: String
)