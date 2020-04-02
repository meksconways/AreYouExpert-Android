package com.meksconway.areyouexpert.home

import android.app.Activity
import com.meksconway.areyouexpert.base.BaseActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.meksconway.areyouexpert.R

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
    }


}
