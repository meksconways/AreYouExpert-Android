package com.meksconway.areyouexpert.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.meksconway.areyouexpert.enums.QuestionType

@Entity(tableName = "questions_table")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val difficulty: String,
    val question: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>?,
    val type: QuestionType
)

