package com.meksconway.areyouexpert.ui.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import kotlin.math.roundToInt


class QuizTimerView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {


    var startColor: Int = ContextCompat.getColor(context, R.color.catArtPrimaryColor)
    var endColor: Int = ContextCompat.getColor(context, R.color.catArtLightColor)
    var defaultTimer = 15
    var listener: QuizTimerViewListener? = null

    private val progressView: RoundCornerProgressBar
    private val timerText: TextView
    private var anim: ObjectAnimator? = null

    fun setProgressColor(color: Int) {
        progressView.progressColor = ContextCompat.getColor(context, color)
    }

    fun setProgressBackgroundColor(color: Int) {
        progressView.secondaryProgressColor = ContextCompat.getColor(context, color)
    }

    init {
        View.inflate(context, R.layout.view_quiz_timer, this)
        progressView = findViewById(R.id.prQuizTimer)
        timerText = findViewById(R.id.txtQuizTimer)
        timerText.text = defaultTimer.toString()
    }

    fun stopTimer() {
        anim?.cancel()
        anim = null
    }

    fun startTimer() {
        timerText.gone()
        anim?.cancel()
        anim = null
        anim = ObjectAnimator.ofFloat(progressView, "progress", timerText.text.toString().toFloat(), 15f)
        anim?.duration = 300
        anim?.interpolator = LinearInterpolator()
        timerText.text = defaultTimer.toString()
        anim?.start()
        anim?.doOnEnd {
            timerText.visible()
            it.cancel()
            anim = null
            anim = ObjectAnimator.ofFloat(progressView, "progress", 15f, 0f)
            anim?.duration = (defaultTimer * 1000).toLong()
            anim?.interpolator = LinearInterpolator()
            anim?.addUpdateListener {
                if (it.animatedValue.toString().toDouble().roundToInt() < 1) {
                    listener?.timeIsUp()
                    return@addUpdateListener
                }
                timerText.text = it.animatedValue.toString().toDouble().roundToInt().toString()
            }
            anim?.start()
        }

        progressView.postDelayed({
            anim?.start()
        }, 700)

    }

    fun addListener(listener: QuizTimerViewListener) {
        this.listener = listener
    }

    interface QuizTimerViewListener {
        fun timeIsUp()
    }


}