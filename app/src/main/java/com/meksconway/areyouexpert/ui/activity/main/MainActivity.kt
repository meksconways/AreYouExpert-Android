package com.meksconway.areyouexpert.ui.activity.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.appbar.MaterialToolbar
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.fragment.home.HomeFragment
import com.meksconway.areyouexpert.util.ToolbarConfigration
import com.meksconway.areyouexpert.viewmodel.ViewModelFactory
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import com.trendyol.medusalib.navigator.Navigator
import com.trendyol.medusalib.navigator.NavigatorConfiguration
import com.trendyol.medusalib.navigator.transaction.NavigatorTransaction
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), Navigator.NavigatorListener {


    private val rootFragmentProvider: List<() -> Fragment> = listOf { HomeFragment() }

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: MainAcitivityViewModel by viewModels {
        factory
    }

    val multipleStackNavigator: MultipleStackNavigator =
        MultipleStackNavigator(
            supportFragmentManager,
            R.id.container,
            rootFragmentProvider,
            navigatorListener = this,
            navigatorConfiguration = NavigatorConfiguration(
                0,
                true, NavigatorTransaction.ATTACH_DETACH
            )
        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        multipleStackNavigator.initialize(savedInstanceState)
        setupActionBar(toolbar)

        observeViewModel()
    }

    private fun setupActionBar(toolbar: MaterialToolbar?) {
        setSupportActionBar(toolbar)
    }

    private fun observeViewModel() {
        viewModel.toolbarConfigOutput.observe(this, Observer {
            actionBar?.title = it.title
            if (it.visible) toolbar.visible() else toolbar.gone()
            supportActionBar?.setHomeButtonEnabled(it.canBack)
            supportActionBar?.setDisplayHomeAsUpEnabled(it.canBack)
            if (it.canBack) toolbar.setNavigationIcon(R.drawable.ic_back)
            supportActionBar?.title = it.title

        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        multipleStackNavigator.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
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
