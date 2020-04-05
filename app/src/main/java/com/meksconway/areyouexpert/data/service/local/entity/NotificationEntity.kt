package com.meksconway.areyouexpert.data.service.local.entity

import android.os.Message
import androidx.appcompat.widget.DialogTitle
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "notification_table")
data class NotificationEntity(
    @PrimaryKey(autoGenerate = true)
    val nofiticationId: Int,
    val title: DialogTitle,
    val message: Message,
    val date: Date
)