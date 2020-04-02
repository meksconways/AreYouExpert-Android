package com.meksconway.areyouexpert.local

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

}