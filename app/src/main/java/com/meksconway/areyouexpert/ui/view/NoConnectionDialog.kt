package com.meksconway.areyouexpert.ui.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.meksconway.areyouexpert.R
import kotlinx.android.synthetic.main.custom_dialog.*

class NoConnectionDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog)
        btn_go_on.setOnClickListener {
            if (this.isShowing)this.dismiss()
        }
    }
}