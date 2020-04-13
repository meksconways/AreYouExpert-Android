package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.datasource.remote.TriviaRemoteDataSource
import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponseResults
import com.meksconway.areyouexpert.domain.repository.QuizRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class QuizRepositoryImpl
@Inject constructor(
    private val remote: TriviaRemoteDataSource,
    private val local: RoomLocalDataSource
) : QuizRepository {


    override fun getCategoryQuestions(categoryId: Int): Observable<MutableList<QuestionsResponseResults>?> {
        return remote.getQuestions(null, categoryId, null, null, null)
            .map {
                it.results
            }
            .subscribeOn(Schedulers.io())
    }

    override fun insertCategoryProgressEntity(progressEntity: CategoryProgressEntity): Completable {
        return Completable.fromAction {
            local.insertProgressEntity(progressEntity)
        }
            .subscribeOn(Schedulers.io())
    }

    override fun updateQuizCategoryProgress(categoryId: Int): Completable {
        return Completable.fromAction {
            local.updateQuiz(categoryId)
        }
            .subscribeOn(Schedulers.io())
    }


}