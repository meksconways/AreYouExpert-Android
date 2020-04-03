package com.meksconway.areyouexpert.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.meksconway.areyouexpert.data.QuizCategoryGradientType

class Converters {

    @TypeConverter
    fun fromQuizCategoryGradientType(value: QuizCategoryGradientType): String {
        val type = object : TypeToken<QuizCategoryGradientType>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toQuizCategoryGradientType(value: String): QuizCategoryGradientType {
        val type = object : TypeToken<QuizCategoryGradientType>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromIncorrectAnswers(value: List<String>): String {
        val type = object : TypeToken<String>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun toIncorrectAnswers(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, type)
    }




}