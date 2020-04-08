package com.meksconway.areyouexpert.domain.repository

import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Observable

interface NotificationRepository {
    fun getLocalNotificationList() : Observable<Res<List<NotificationEntity>>>
    fun insertLocalNotification(notification: NotificationEntity)
    fun insertLocalNotificationList(notification: List<NotificationEntity>)
    fun deleteLocalNotificationList()
}