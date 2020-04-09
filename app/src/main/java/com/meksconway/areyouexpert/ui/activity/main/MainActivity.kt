package com.meksconway.areyouexpert.ui.activity.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.ui.fragment.home.HomeFragment
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity(), Navigator.NavigatorListener {


    private val rootFragmentProvider: List<() -> Fragment> = listOf { HomeFragment() }

    val multipleStackNavigator: MultipleStackNavigator =
        MultipleStackNavigator(
            supportFragmentManager,
            R.id.container,
            rootFragmentProvider,
            navigatorListener = this,
            navigatorConfiguration = NavigatorConfiguration(0,
                true, NavigatorTransaction.SHOW_HIDE)
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        multipleStackNavigator.initialize(savedInstanceState)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        multipleStackNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }


    override fun onBackPressed() {
        if (multipleStackNavigator.canGoBack()) {
            multipleStackNavigator.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onTabChanged(tabIndex: Int) {

    }


}
