package com.meksconway.areyouexpert.data.service.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categoryprogress_entity")
data class CategoryProgressEntity(
    @PrimaryKey(autoGenerate = true)
    val categoryProgressId: Int = 0,
    val categoryName: String,
    val progress: Int
)