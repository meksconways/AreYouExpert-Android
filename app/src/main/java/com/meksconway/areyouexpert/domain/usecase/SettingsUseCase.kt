package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUseCase
@Inject constructor(private val repository: SettingsRepository) {

    fun getSettingsList(): List<SettingsModel> {
        val arr = arrayListOf<SettingsModel>()
        arr.add(
            SettingsModel(
                settingsId = 0,
                settingsTitle = "Reset Your Progress"
            )
        )
        arr.add(
            SettingsModel(
                settingsId = 1,
                settingsTitle = "Vote App"
            )
        )
        arr.add(
            SettingsModel(
                settingsId = 2,
                settingsTitle = "Open Source"
            )
        )
        arr.add(
            SettingsModel(
                settingsId = 3,
                settingsTitle = "Make Suggestion"
            )
        )
        return arr
    }
}

data class SettingsModel(
    val settingsId: Int,
    val settingsTitle: String
)