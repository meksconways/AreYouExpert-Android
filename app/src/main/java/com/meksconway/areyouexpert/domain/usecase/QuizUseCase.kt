package com.meksconway.areyouexpert.domain.usecase

import com.meksconway.areyouexpert.common.QuizCategoryResources
import com.meksconway.areyouexpert.data.service.local.entity.CategoryProgressEntity
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponseResults
import com.meksconway.areyouexpert.domain.repository.QuizRepository
import com.meksconway.areyouexpert.util.Res
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuizUseCase @Inject constructor(
    private val repository: QuizRepository
) {

    private var _categoryModel: CategoryModel? = null

    fun getQuestions(category: CategoryModel): Observable<Res<List<QuestionsResponseResults>>> {
        _categoryModel = category
        return Observable.create<Res<List<QuestionsResponseResults>>> { emitter ->
            emitter.onNext(Res.loading())
            repository.getCategoryQuestions(category.id)
                .subscribe(
                    {
                        if (!it.isNullOrEmpty()) {
                            it.mapIndexed { index, result ->
                                result.totalQuestion = it.size
                                result.questionId = index
                                val answersArr = arrayListOf<Answer>()
                                answersArr.add(Answer(result.correctAnswer, category.resources))
                                result.incorrectAnswers.map { ia ->
                                    answersArr.add(
                                        Answer(
                                            ia,
                                            category.resources
                                        )
                                    )
                                }
                                answersArr.shuffle()
                                result.answers = arrayListOf()
                                result.answers.addAll(answersArr)
                            }
                            emitter.onNext(Res.success(it))
                        }
                    },
                    {
                        emitter.onNext(Res.error(it))
                    }
                )
        }
    }

    fun updateCategoryProgressAndQuizCategoryProgress(
        progress: Int,
        model: CategoryModel
    ): Observable<Res<Boolean>> {


        return Observable.create<Res<Boolean>> { emitter ->
            emitter.onNext(Res.loading())
            repository.insertCategoryProgressEntity(
                CategoryProgressEntity(
                    categoryName = model.name,
                    progress = progress
                )
            ).concatWith {
                repository.updateQuizCategoryProgress(model.id).subscribe(
                    {
                        emitter.onNext(Res.success(true))
                    },
                    {
                        emitter.onNext(Res.error(it))
                    }
                )
            }
                .subscribe()
        }

    }

}


data class Answer(
    val answerText: String,
    val resources: QuizCategoryResources,
    var isSelected: Boolean = false,
    var disabled: Boolean = false
)
