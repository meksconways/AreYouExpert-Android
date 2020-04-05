package com.meksconway.areyouexpert.common

import com.meksconway.areyouexpert.enums.CategoryResIdAndGradient

object Decider {

    fun getCategoryResources(id: Int): QuizCategoryResources {
        return when (id) {
            9 -> CategoryResIdAndGradient.GENERAL.resources
            10 -> CategoryResIdAndGradient.BOOKS.resources
            11 -> CategoryResIdAndGradient.FILM.resources
            12 -> CategoryResIdAndGradient.MUSIC.resources
            13 -> CategoryResIdAndGradient.MUSICALS_THEATRES.resources
            14 -> CategoryResIdAndGradient.TELEVISION.resources
            15 -> CategoryResIdAndGradient.VIDEO_GAMES.resources
            16 -> CategoryResIdAndGradient.BOARD_GAMES.resources
            17 -> CategoryResIdAndGradient.SCIENCE_NATURE.resources
            18 -> CategoryResIdAndGradient.COMPUTERS.resources
            19 -> CategoryResIdAndGradient.MATHEMATICS.resources
            20 -> CategoryResIdAndGradient.MYTHOLOGY.resources
            21 -> CategoryResIdAndGradient.SPORTS.resources
            22 -> CategoryResIdAndGradient.GEOGRAPHY.resources
            23 -> CategoryResIdAndGradient.HISTORY.resources
            24 -> CategoryResIdAndGradient.POLITICS.resources
            25 -> CategoryResIdAndGradient.ART.resources
            26 -> CategoryResIdAndGradient.CELEBRITIES.resources
            27 -> CategoryResIdAndGradient.ANIMALS.resources
            28 -> CategoryResIdAndGradient.VEHICLES.resources
            29 -> CategoryResIdAndGradient.COMICS.resources
            30 -> CategoryResIdAndGradient.GADGETS.resources
            31 -> CategoryResIdAndGradient.JAPANESE_ANIME_MANGA.resources
            32 -> CategoryResIdAndGradient.CARTOON_ANIMATION.resources

            else -> CategoryResIdAndGradient.GENERAL.resources
        }
    }

}