package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.domain.repository.NotificationRepository
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationUseCase
@Inject constructor(private val repository: NotificationRepository) {

    fun getLocalNotificationList(): Observable<Res<List<NotificationEntity>>> {
        return repository.getLocalNotificationList().map {
            it
        }
    }

    fun deleteAllNotificationList(): Completable {
        return Completable.fromAction {
            repository.deleteLocalNotificationList()
        }.subscribeOn(Schedulers.io())
    }

}