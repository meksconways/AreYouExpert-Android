package com.meksconway.areyouexpert.data.service.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

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