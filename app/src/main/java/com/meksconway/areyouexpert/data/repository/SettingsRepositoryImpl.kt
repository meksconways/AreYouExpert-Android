package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject constructor(private val source: RoomLocalDataSource) : SettingsRepository {

}