package com.meksconway.areyouexpert.common

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class QuizCategoryResources(
    @DrawableRes
    val drawableRes: Int,
    @ColorRes
    val primaryColor: Int,
    @ColorRes
    val darkColor: Int,
    @ColorRes
    val lightColor: Int)