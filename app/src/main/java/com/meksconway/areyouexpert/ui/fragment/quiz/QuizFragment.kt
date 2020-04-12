package com.meksconway.areyouexpert.ui.fragment.quiz

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.ui.view.NoConnectionDialog
import com.meksconway.areyouexpert.ui.view.QuizTimerView
import kotlinx.android.synthetic.main.quiz_fragment.*

class QuizFragment : Fragment() {

    companion object {
        fun newInstance() = QuizFragment()
    }

    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quiz_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)

        quizTimerView.addListener(object : QuizTimerView.QuizTimerViewListener {
            override fun timeIsUp() {

            }
        })

        btn.setOnClickListener {
            quizTimerView.startTimer()
        }
    }

}
