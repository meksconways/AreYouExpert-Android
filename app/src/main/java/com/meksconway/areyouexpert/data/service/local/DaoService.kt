package com.meksconway.areyouexpert.data.service.local

import androidx.room.*
import com.meksconway.areyouexpert.data.service.local.entity.QuestionEntity
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import io.reactivex.Single

@Dao
interface DaoService {

    @Query("SELECT * FROM quiz_category")
    fun getQuizList(): Single<List<QuizCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuiz(quizCategory: QuizCategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuizList(quizCategory: List<QuizCategoryEntity>)

    @Query("DELETE FROM quiz_category")
    fun deleteQuizList()

//    @Query("SELECT * FROM questions_table")
//    fun getQuestions(): Single<List<QuestionEntity>>

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertQuestion(question: QuestionEntity)

//    @Query("DELETE FROM questions_table")
//    fun deleteQuestions()



}