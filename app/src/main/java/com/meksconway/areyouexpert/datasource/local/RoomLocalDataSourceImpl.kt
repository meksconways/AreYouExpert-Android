package com.meksconway.areyouexpert.datasource.local

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.service.local.DaoService
import com.meksconway.areyouexpert.data.service.local.ExpertDatabase
import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import io.reactivex.Observable
import javax.inject.Inject

class RoomLocalDataSourceImpl
@Inject constructor(private val daoService: DaoService,
                    private val db: ExpertDatabase) : RoomLocalDataSource {

    override fun dropDatabase() {
        db.clearAllTables()
    }

    override fun getQuizList(): Observable<List<QuizCategoryEntity>> = daoService
        .getQuizList().toObservable()

    override fun insertQuiz(quizCategory: QuizCategoryEntity) {
        daoService.insertQuiz(quizCategory)
    }

    override fun insertQuizList(quizCategory: List<QuizCategoryEntity>) {
        daoService.insertQuizList(quizCategory)
    }

    override fun updateQuiz(categoryId: Int) {
        daoService.increateQuizProgress(categoryId)
    }

    override fun deleteQuizList() {
        daoService.deleteQuizList()
    }

    override fun getNotificationList(): Observable<List<NotificationEntity>> = daoService
        .getNotificationList().toObservable()

    override fun insertNotification(notification: NotificationEntity) {
        daoService.insertNotification(notification)
    }

    override fun insertNotificationList(notification: List<NotificationEntity>) {
        daoService.insertNotificationList(notification)
    }

    override fun deleteAllNotification() {
        daoService.deleteNotificationList()
    }

    override fun getCategoryProgressEntity(): Observable<List<CategoryProgressEntity>> {
        return daoService.getCategoryProgressEntity().toObservable()
    }

    override fun insertProgressEntity(progressEntity: CategoryProgressEntity) {
        daoService.insertCategoryProgress(progressEntity)
    }


}