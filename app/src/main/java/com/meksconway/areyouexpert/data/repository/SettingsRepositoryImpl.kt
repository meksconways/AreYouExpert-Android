package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.domain.repository.SettingsRepository
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SettingsRepositoryImpl
@Inject constructor(private val source: RoomLocalDataSource) : SettingsRepository {

    override fun dropDatabase(): Completable {
        return Completable.fromAction {
            source.dropDatabase()
        }
            .subscribeOn(Schedulers.io())
    }
}