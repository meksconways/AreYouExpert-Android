package com.meksconway.areyouexpert.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.meksconway.areyouexpert.R
import kotlinx.android.synthetic.main.dialog_reset_progress.*

class ResetProgressDialog(context: Context, private val callback: (Boolean) -> Unit) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_reset_progress)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnYes.setOnClickListener{
            callback.invoke(true)
            this.dismiss()
        }
        btnNo.setOnClickListener{
            callback(false)
            this.dismiss()
        }
    }

}