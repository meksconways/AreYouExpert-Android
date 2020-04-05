package com.meksconway.areyouexpert.data.repository

import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import com.meksconway.areyouexpert.data.datasource.remote.TriviaRemoteDataSource
import com.meksconway.areyouexpert.data.service.local.entity.QuizCategoryEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuizCategories
import com.meksconway.areyouexpert.domain.repository.HomeRepository
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.remote
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepositoryImpl
@Inject constructor(
    private val remoteDataSource: TriviaRemoteDataSource,
    private val roomLocalDataSource: RoomLocalDataSource
) : HomeRepository {


    override fun getRemoteCategories(): Observable<Res<List<QuizCategories>>> {
        return remoteDataSource.getCategories()
            .map {
                it.categories
            }.remote()
    }

    override fun getLocalCategories(): Observable<List<QuizCategoryEntity>> {
        return roomLocalDataSource.getQuizList()
            .subscribeOn(Schedulers.io())
    }

    override fun insertCategory(categoryEntity: QuizCategoryEntity) {
        roomLocalDataSource.insertQuiz(categoryEntity)
    }

    override fun insertCategoryList(categoryEntity: List<QuizCategoryEntity>) {
        roomLocalDataSource.insertQuizList(categoryEntity)
    }


}
