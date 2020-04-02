package com.meksconway.areyouexpert.local

import androidx.room.*
import com.meksconway.areyouexpert.local.entity.QuestionEntity
import com.meksconway.areyouexpert.local.entity.QuizCategoryEntity
import io.reactivex.Single

@Dao
interface DaoService {

    @Query("SELECT * FROM quiz_category")
    fun getQuizList(): Single<List<QuizCategoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuizList(quizCategory: QuizCategoryEntity)

    @Query("DELETE FROM quiz_category")
    fun deleteQuizList()

    @Query("SELECT * FROM questions_table")
    fun getQuestions(): Single<List<QuestionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: QuestionEntity)

    @Query("DELETE FROM questions_table")
    fun deleteQuestions()



}