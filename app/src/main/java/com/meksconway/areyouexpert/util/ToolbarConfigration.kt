package com.meksconway.areyouexpert.util

import androidx.annotation.MenuRes

data class ToolbarConfigration(
    val title: String,
    val visible: Boolean = true,
    val canBack: Boolean = false
)