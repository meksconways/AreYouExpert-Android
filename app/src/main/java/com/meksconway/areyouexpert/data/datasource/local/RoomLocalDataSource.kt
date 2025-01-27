package com.meksconway.areyouexpert.data.datasource.local

import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import io.reactivex.Observable

interface RoomLocalDataSource {

    fun dropDatabase()

    fun getQuizList(): Observable<List<QuizCategoryEntity>>
    fun insertQuiz(quizCategory: QuizCategoryEntity)
    fun insertQuizList(quizCategory: List<QuizCategoryEntity>)
    fun updateQuiz(categoryId: Int)
    fun deleteQuizList()

    fun getNotificationList(): Observable<List<NotificationEntity>>
    fun insertNotification(notification: NotificationEntity)
    fun insertNotificationList(notification : List<NotificationEntity>)
    fun deleteAllNotification()

    fun getCategoryProgressEntity(): Observable<List<CategoryProgressEntity>>
    fun insertProgressEntity(progressEntity: CategoryProgressEntity)

}