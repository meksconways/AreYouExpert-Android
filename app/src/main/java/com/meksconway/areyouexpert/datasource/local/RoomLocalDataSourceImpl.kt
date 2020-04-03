package com.meksconway.areyouexpert.datasource.local

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.service.local.DaoService
import com.meksconway.areyouexpert.data.service.local.entity.QuestionEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import io.reactivex.Observable
import javax.inject.Inject

class RoomLocalDataSourceImpl
@Inject constructor(private val daoService: DaoService) : RoomLocalDataSource {

    override fun getQuizList(): Observable<List<QuizCategoryEntity>> = daoService
        .getQuizList().toObservable()

    override fun insertQuizList(quizCategory: QuizCategoryEntity) {
        daoService.insertQuizList(quizCategory)
    }

    override fun deleteQuizList() {
        daoService.deleteQuizList()
    }

    override fun getQuestions(): Observable<List<QuestionEntity>> = daoService
        .getQuestions().toObservable()


    override fun insertQuestion(question: QuestionEntity) {
        daoService.insertQuestion(question)
    }

    override fun deleteQuestions() {
        daoService.deleteQuestions()
    }


}