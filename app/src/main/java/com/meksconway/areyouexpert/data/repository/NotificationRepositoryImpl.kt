package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.domain.repository.NotificationRepository
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.local
import io.reactivex.Observable
import javax.inject.Inject

class NotificationRepositoryImpl
@Inject constructor(
    private val roomLocalDataSource: RoomLocalDataSource
) : NotificationRepository {
    override fun getLocalNotificationList(): Observable<Res<List<NotificationEntity>>> =
         roomLocalDataSource.getNotificationList()
             .local()

    override fun insertLocalNotification(notification: NotificationEntity) =
        roomLocalDataSource.insertNotification(notification)

    override fun insertLocalNotificationList(notification: List<NotificationEntity>) =
        roomLocalDataSource.insertNotificationList(notification)

    override fun deleteLocalNotificationList() {

    }
}