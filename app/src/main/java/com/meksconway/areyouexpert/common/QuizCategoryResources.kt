package com.meksconway.areyouexpert.common

import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuizCategoryResources(
    @DrawableRes
    val drawableRes: Int,
    @ColorRes
    val primaryColor: Int,
    @ColorRes
    val darkColor: Int,
    @ColorRes
    val lightColor: Int): Parcelable