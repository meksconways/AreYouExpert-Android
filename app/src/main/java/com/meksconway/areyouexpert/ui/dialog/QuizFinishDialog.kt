package com.meksconway.areyouexpert.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.core.content.ContextCompat
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.ui.fragment.quiz.QuizFinishState
import kotlinx.android.synthetic.main.dialog_quiz_finish_state.*

class QuizFinishDialog(
    context: Context, private val state: QuizFinishState,
    private val callback: (QuizFinishState) -> Unit
) : Dialog(context,android.R.style.Theme_Translucent_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window?.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.MATCH_PARENT
//        )
//        window?.setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN)
        window?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#d0000000")))
        setContentView(R.layout.dialog_quiz_finish_state)

        btnDialogQuizTryAgain.setOnClickListener {
            this.dismiss()
            callback.invoke(QuizFinishState.BUTTON_TRY_AGAIN)
        }

        btnDialogQuizBackMenu.setOnClickListener {
            this.dismiss()
            callback.invoke(QuizFinishState.BUTTON_BACK_TO_MENU)
        }

        when (state) {
            QuizFinishState.WRONG_ANSWER -> {
                dialogQuizAnimatedView.setAnimation(R.raw.quiz_failed)
                txtQuizDialogTitle?.text = "Wrong Answer!"
            }
            QuizFinishState.TIME_IS_UP -> {
                dialogQuizAnimatedView.setAnimation(R.raw.time_is_up_anim)
                txtQuizDialogTitle?.text = "Time is Up!"
            }
            QuizFinishState.SUCCESS -> {
                txtQuizDialogTitle?.text = "Congratulations!"
                btnDialogQuizTryAgain.backgroundTintList = ContextCompat.getColorStateList(
                    context,
                    R.color.catScienceAndNatureDarkColor
                )
                dialogQuizAnimatedView.setAnimation(R.raw.anim_suc)

            }
        }



    }

}


