package com.meksconway.areyouexpert.common

import com.meksconway.areyouexpert.enums.CategoryResIdAndGradient

object Decider {

    fun getCategoryResources(id: Int): QuizCategoryResources {
        return when (id) {
            0 -> CategoryResIdAndGradient.GENERAL.resources
            else -> CategoryResIdAndGradient.GENERAL.resources
        }
    }

}