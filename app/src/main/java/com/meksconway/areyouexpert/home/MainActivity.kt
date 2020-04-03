package com.meksconway.areyouexpert.home

import android.app.Activity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.ui.view.NoConnectionDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigation : BottomNavigationView = findViewById(R.id.btm_nav_bar)
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){

                //bottom navigate bar üzerinndeki itemlerin fragmentleri buraya eklenecek

            }
            true

        }
        btn_test.setOnClickListener {
            val dialog = NoConnectionDialog(this)
            dialog.show()
        }
    }


}
