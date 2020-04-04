package com.meksconway.areyouexpert.data.datasource.local

import com.meksconway.areyouexpert.data.service.local.entity.QuestionEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import io.reactivex.Observable

interface RoomLocalDataSource {

    fun getQuizList(): Observable<List<QuizCategoryEntity>>
    fun insertQuiz(quizCategory: QuizCategoryEntity)
    fun insertQuizList(quizCategory: List<QuizCategoryEntity>)
    fun deleteQuizList()
    fun getQuestions(): Observable<List<QuestionEntity>>
    fun insertQuestion(question: QuestionEntity)
    fun deleteQuestions()
}