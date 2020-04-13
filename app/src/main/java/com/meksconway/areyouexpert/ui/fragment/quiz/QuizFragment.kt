package com.meksconway.areyouexpert.ui.fragment.quiz

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.Answer
import com.meksconway.areyouexpert.domain.usecase.CategoryModel
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.adapter.QuizAdapter
import com.meksconway.areyouexpert.ui.dialog.QuizFinishDialog
import com.meksconway.areyouexpert.ui.view.QuizTimerView
import com.meksconway.areyouexpert.util.Status.*
import com.meksconway.areyouexpert.util.ToolbarConfigration
import com.trendyol.medusalib.navigator.Navigator
import kotlinx.android.synthetic.main.quiz_fragment.*

class QuizFragment : BaseFragment<QuizInput, QuizOutput, QuizViewModel>(),
    Navigator.OnGoBackListener {

    companion object {
        fun newInstance(category: CategoryModel): QuizFragment {
            val args = Bundle()
            args.putParcelable("category", category)
            val fragment = QuizFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override val viewModel: QuizViewModel by viewModels {
        factory
    }

    private var _answer: Answer? = null

    private val adapter: QuizAdapter by lazy {
        QuizAdapter { answer ->
            answer?.let {
                btnNext.isEnabled = true
                _answer = it
            } ?: kotlin.run {
                btnNext.isEnabled = false
                _answer = null
            }

        }
    }


    override fun viewDidLoad() {
        super.viewDidLoad()
        arguments?.getParcelable<CategoryModel>("category")?.let {
            viewModel.input.setCategory(it)
        }
        rvQuiz?.adapter = adapter
        rvQuiz?.layoutManager = LinearLayoutManager(context)
        initListener()
        requireActivity()
            .onBackPressedDispatcher.addCallback(this) {
            showPopUp(QuizFinishState.SUCCESS)
        }

    }

    override val layRes: Int
        get() = R.layout.quiz_fragment

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("", false, canBack = true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun observeViewModel(output: QuizOutput?) {

        output?.categoryProgressOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                SUCCESS -> {
                    hideLoading()
                    navigator?.goBack() //todo değişecek
                }

                LOADING -> {
                    showLoading()
                }

                ERROR -> {
                    hideLoading()
                    Toast.makeText(context, it.error?.localizedMessage, Toast.LENGTH_SHORT).show()
                }

            }
        })

        output?.questionsDataOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                SUCCESS -> {
                    hideLoading()
                    quizContainer.visible()
                    viewModel.input.getFirstQuestion(it.data!!)
                }
                ERROR -> {
                    Toast.makeText(context, it.error?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                LOADING -> {
                    showLoading()
                    quizContainer.gone()
                }
            }
        })
        output?.finishState?.observe(viewLifecycleOwner, Observer { state ->
            state?.let {
                showPopUp(state)
                quizTimerView?.stopTimer()
                when (it) {
                    QuizFinishState.SUCCESS -> {
                        //save data
                    }
                    QuizFinishState.WRONG_ANSWER, QuizFinishState.TIME_IS_UP -> {
                        adapter.disableAllItems()
                        btnNext?.isEnabled = false
                    }
                }
            }

        })

        output?.nextQuestionOutput?.observe(viewLifecycleOwner, Observer {
            setAdapter(it.answers)
            quizTimerView.startTimer()
            val questionText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(it.question, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                Html.fromHtml(it.question)
            }
            txtQuizTitle?.text = it.getQuestionTitleText()
            txtQuestion?.text = questionText
            quizTimerView.setProgressColor(it.answers[0].resources.primaryColor)
            quizTimerView.setProgressBackgroundColor(it.answers[0].resources.lightColor)
            btnNext.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                it.answers[0].resources.primaryColor
            )
        })
    }

    private fun showPopUp(state: QuizFinishState) {
        QuizFinishDialog(requireContext(), state) {
            //route to quiz result page
            viewModel.input.updateDb()
        }.apply {
            setCancelable(false)
            show()
        }
    }

    private fun setAdapter(result: List<Answer>) {
        adapter.setItems(result)
    }

    private fun initListener() {
        quizTimerView.addListener(object : QuizTimerView.QuizTimerViewListener {
            override fun timeIsUp() {
                viewModel.setTimeisUp()
            }
        })

        btnNext.setOnClickListener {
            _answer?.let {
                viewModel.input.getNextQuestion(it)
                _answer = null
            }

        }

    }

    override fun onGoBack(): Boolean {
        return true
    }


}
