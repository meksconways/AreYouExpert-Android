package com.meksconway.areyouexpert.ui.fragment.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.meksconway.areyouexpert.data.service.remote.model.QuestionsResponseResults
import com.meksconway.areyouexpert.domain.usecase.Answer
import com.meksconway.areyouexpert.domain.usecase.CategoryModel
import com.meksconway.areyouexpert.domain.usecase.QuizUseCase
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import com.meksconway.areyouexpert.viewmodel.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

interface QuizInput : Input {
    fun getQuestions(category: CategoryModel)
    fun setCategory(category: CategoryModel)
    fun getNextQuestion(answer: Answer)
    fun getFirstQuestion(list: List<QuestionsResponseResults>)
    fun setTimeisUp()
    fun updateDb(state: QuizFinishState)
}

interface QuizOutput : Output {
    val questionsDataOutput: LiveData<Res<List<QuestionsResponseResults>>>
    val nextQuestionOutput: LiveData<QuestionsResponseResults>
    val finishState: LiveData<QuizFinishState>
    val backToMenuOutput: LiveData<Res<Boolean>>
    val tryAgainOutput: LiveData<Res<Boolean>>
}

class QuizViewModel @Inject constructor(
    private val useCase: QuizUseCase
) : BaseViewModel<QuizInput, QuizOutput>(), QuizInput, QuizOutput {

    override val input: QuizInput
        get() = this
    override val output: QuizOutput
        get() = this

    private var _page: Int = 0
    private var _questionList = arrayListOf<QuestionsResponseResults>()
    private var _categoryModel: CategoryModel? = null


    override fun getQuestions(category: CategoryModel) {
        useCase.getQuestions(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _questionsOutput.value = it
            }
            .addTo(compositeDisposable)
    }

    override fun setCategory(category: CategoryModel) {
        getQuestions(category)
        _categoryModel = category
    }

    override fun getFirstQuestion(list: List<QuestionsResponseResults>) {
        _questionList.addAll(list)
        _nextQuestionOutput.value = _questionList[_page]
    }

    override fun setTimeisUp() {
        _finishState.value = QuizFinishState.TIME_IS_UP
    }

    override fun updateDb(state: QuizFinishState) {
        _categoryModel?.let {
            useCase.updateCategoryProgressAndQuizCategoryProgress(_page + 1, it)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result ->
                    when (state) {
                        QuizFinishState.BUTTON_BACK_TO_MENU -> {
                            _backToMenuOutput.value = result
                        }
                        QuizFinishState.BUTTON_TRY_AGAIN -> {
                            _tryAgainOutput.value = result
                        }
                    }
                }
                .addTo(compositeDisposable)
        }
    }

    override fun getNextQuestion(answer: Answer) {
        if (answer.answerText == _questionList[_page].correctAnswer) {
            if (_page + 1 > _questionList.size) {
                _finishState.value = QuizFinishState.SUCCESS
            } else {
                _page += 1
                _nextQuestionOutput.value = _questionList[_page]
            }
        } else {
            _finishState.value = QuizFinishState.WRONG_ANSWER
        }

    }

    fun clearQuiz() {
        _page = 0
        _questionList = arrayListOf()
        _categoryModel = null
        _questionsOutput.value = Res.loading()
        _nextQuestionOutput.value = null

    }

    private val _questionsOutput = MutableLiveData<Res<List<QuestionsResponseResults>>>()
    override val questionsDataOutput: LiveData<Res<List<QuestionsResponseResults>>>
        get() = _questionsOutput

    private val _nextQuestionOutput = MutableLiveData<QuestionsResponseResults>()
    override val nextQuestionOutput: LiveData<QuestionsResponseResults>
        get() = _nextQuestionOutput

    private val _finishState = SingleLiveEvent<QuizFinishState>()
    override val finishState: LiveData<QuizFinishState>
        get() = _finishState

    private val _backToMenuOutput = MutableLiveData<Res<Boolean>>()
    override val backToMenuOutput: LiveData<Res<Boolean>>
        get() = _backToMenuOutput

    private val _tryAgainOutput = MutableLiveData<Res<Boolean>>()
    override val tryAgainOutput: LiveData<Res<Boolean>>
        get() = _tryAgainOutput

}

enum class QuizFinishState {
    SUCCESS,
    TIME_IS_UP,
    WRONG_ANSWER,
    BUTTON_BACK_TO_MENU,
    BUTTON_TRY_AGAIN
}
