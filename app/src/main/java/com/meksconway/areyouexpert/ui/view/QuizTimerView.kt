package com.meksconway.areyouexpert.ui.view

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.meksconway.areyouexpert.R
import kotlinx.coroutines.delay
import kotlin.math.roundToInt


class QuizTimerView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {


    var startColor: Int = ContextCompat.getColor(context, R.color.catArtPrimaryColor)
    var endColor: Int = ContextCompat.getColor(context, R.color.catArtLightColor)
    var defaultTimer = 15
    var listener: QuizTimerViewListener? = null

    private val progressView: RoundCornerProgressBar
    private val timerText: TextView

    init {
        View.inflate(context, R.layout.view_quiz_timer, this)
        progressView = findViewById(R.id.prQuizTimer)
        timerText = findViewById(R.id.txtQuizTimer)
        timerText.text = defaultTimer.toString()
    }

    fun startTimer() {
        val anim: ObjectAnimator = ObjectAnimator.ofFloat(progressView,"progress",15f,1f)
        anim.duration = (defaultTimer * 1000).toLong()
        anim.interpolator = LinearInterpolator()
        anim.addUpdateListener {
            timerText.text = it.animatedValue.toString().toDouble().roundToInt().toString()
        }
        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
                listener?.timeIsUp()
            }
        })
        progressView.postDelayed({
            anim.start()
        },1000)

    }

    fun addListener(listener: QuizTimerViewListener){
        this.listener = listener
    }

    interface QuizTimerViewListener {
        fun timeIsUp()
    }



}