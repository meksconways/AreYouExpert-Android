package com.meksconway.areyouexpert.ui.view

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.data.datasource.local.RoomLocalDataSource
import kotlinx.android.synthetic.main.custom_dialog_drop_db.*

class DropDataBaseDialog(context: Context, private val db: RoomLocalDataSource) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_dialog_drop_db)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        btn_yes.setOnClickListener{
            db.dropDataBase()
        }
        btn_no.setOnClickListener{
            if (this.isShowing)this.dismiss()
        }
    }

}