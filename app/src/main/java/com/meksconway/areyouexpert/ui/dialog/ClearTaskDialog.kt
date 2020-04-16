package com.meksconway.areyouexpert.ui.dialog


import android.animation.Animator
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity.CENTER
import android.view.Window
import android.widget.FrameLayout
import com.airbnb.lottie.LottieAnimationView
import com.meksconway.areyouexpert.R


class ClearTaskDialog(context: Context,
                      private val callback: () -> Unit) : Dialog(context) {

    private lateinit var _frameLayout: FrameLayout
    private lateinit var _lottieView: LottieAnimationView

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        setCancelable(false)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        createContent()
    }

    private fun createContent() {
        _frameLayout = FrameLayout(context)
        _lottieView = LottieAnimationView(context)
        _lottieView.setAnimation(R.raw.anim_delete)
        _lottieView.setBackgroundColor(Color.TRANSPARENT)
        _lottieView.playAnimation()
        _lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                this@ClearTaskDialog.dismiss()
                callback.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = CENTER
        }
        params.setMargins(32, 32, 32, 32)
        _lottieView.layoutParams = params
        _frameLayout.addView(_lottieView)

        setContentView(_frameLayout)
    }


}
