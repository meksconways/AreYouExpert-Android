package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.common.QuizCategoryGradientType
import com.meksconway.areyouexpert.enums.BannerCategory

class HomeUseCase {
}

data class HomeBannerModel(
    val bannerId: Int,
    val bannerImage: String,
    val category: BannerCategory
)

data class TitleModel(val title: String)

data class CategoriesModel(
    val id: Int,
    val progress: Int,
    val name: String,
    val gradient: QuizCategoryGradientType
)

enum class ContentItemType {
    BANNER,
    TITLE,
    CATEGORY
}

interface HomeItemType {
    fun getItemType(): ContentItemType
}