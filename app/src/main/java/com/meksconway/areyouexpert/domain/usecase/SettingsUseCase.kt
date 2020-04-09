package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.domain.repository.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsUseCase
@Inject constructor(private val repository: SettingsRepository) {

    fun getSettingsList(): List<SettingsListModel> {
        val arr = arrayListOf<SettingsListModel>()
        arr.add(
            SettingsListModel(
                settingsId = 0,
                settingsTitle = "Reset Your Progress"
            )
        )
        arr.add(
            SettingsListModel(
                settingsId = 1,
                settingsTitle = "Vote App"
            )
        )
        arr.add(
            SettingsListModel(
                settingsId = 2,
                settingsTitle = "Open Source"
            )
        )
        arr.add(
            SettingsListModel(
                settingsId = 3,
                settingsTitle = "Make Suggestion"
            )
        )
        return arr
    }
}

data class SettingsListModel(
    val settingsId: Int,
    val settingsTitle: String
)