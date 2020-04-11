package com.meksconway.areyouexpert.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.meksconway.areyouexpert.R
import kotlinx.android.synthetic.main.custom_dialog_drop_db.*

class DropDatabaseDialog(context: Context, private val callback: (Boolean) -> Unit) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_drop_db)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btnYes.setOnClickListener{
            callback.invoke(true)
        }
        btnNo.setOnClickListener{
            callback(false)
        }
    }

}