package com.meksconway.areyouexpert.domain.repository

import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

interface SettingsRepository {

    fun dropDatabase(): Completable
}