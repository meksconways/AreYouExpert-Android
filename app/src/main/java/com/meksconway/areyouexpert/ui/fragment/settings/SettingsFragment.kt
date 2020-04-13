package com.meksconway.areyouexpert.ui.fragment.settings

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.codemybrainsout.ratingdialog.RatingDialog
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.SettingsItemType.*
import com.meksconway.areyouexpert.domain.usecase.SettingsModel
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.activity.splash.SplashActivity
import com.meksconway.areyouexpert.ui.adapter.SettingsAdapter
import com.meksconway.areyouexpert.ui.fragment.makesuggestion.MakeSuggestionFragment
import com.meksconway.areyouexpert.ui.view.ResetProgressDialog
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import com.meksconway.areyouexpert.util.ToolbarConfigration
import kotlinx.android.synthetic.main.settings_fragment.*


class SettingsFragment :
    BaseFragment<SettingsViewModelInput, SettingsViewModelOutput, SettingsViewModel>() {

    override val layRes: Int
        get() = R.layout.settings_fragment

    private val adapter = SettingsAdapter {
        when (it.type) {
            MAKE_SUGGESTION -> {
                navigator?.start(MakeSuggestionFragment())
            }
            RESET_PROGRESS -> {
                showResetProgressDialog()
            }
            VOTE -> {
                showVoteDialog()
            }
            OPEN_SOURCE -> {
                // show open source fragment
            }
        }
    }

    private fun showResetProgressDialog() {
        ResetProgressDialog(requireContext()) {
            if (it) viewModel.input.resetProgress()
        }.show()

    }

    private val layoutManager = LinearLayoutManager(context)

    override val viewModel: SettingsViewModel by viewModels {
        factory
    }

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("Settings", visible = true, canBack = canBack)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()

    }

    override fun observeViewModel(output: SettingsViewModelOutput?) {
        output?.settingsOutput?.observe(viewLifecycleOwner, Observer {
            checkSettingsContentOutput(it)
        })
        output?.resetProgressOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.gone()
                    rvSettings.visible()
                    startActivity(Intent(context, SplashActivity::class.java))
                    activity?.finish()
                }
                Status.LOADING -> {
                    progressBar.visible()
                    rvSettings.gone()
                }

                Status.ERROR -> {
                    progressBar.gone()
                    rvSettings.visible()
                    Toast.makeText(context, it.error?.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun checkSettingsContentOutput(resource: Res<List<SettingsModel>>) {
        if (resource.data != null) {
            setAdapter(resource.data)
        }
    }

    private fun setAdapter(list: List<SettingsModel>?) {
        rvSettings?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        list?.let {
            rvSettings?.adapter = adapter
            rvSettings?.layoutManager = LinearLayoutManager(context)
            adapter.setItems(it)
        }

    }

    private fun showVoteDialog() {
        val ratingDialog = RatingDialog.Builder(context)
            .threshold(3f)
            .ratingBarColor(R.color.colorPrimary)
            .playstoreUrl("https://github.com/codemybrainsout/smart-app-rate")
            .build()
        ratingDialog.show()
    }


}
