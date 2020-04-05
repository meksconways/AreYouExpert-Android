package com.meksconway.areyouexpert.data.service.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notification_table")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val nofiticationId: Int,
    val notificationTitle: String,
    val notificationMessage: String
)