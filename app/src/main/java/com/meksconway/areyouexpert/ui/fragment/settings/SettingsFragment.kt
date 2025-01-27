package com.meksconway.areyouexpert.ui.fragment.settings

import android.content.Intent
import android.widget.Toast
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
import com.meksconway.areyouexpert.ui.dialog.ClearTaskDialog
import com.meksconway.areyouexpert.ui.fragment.makesuggestion.MakeSuggestionFragment
import com.meksconway.areyouexpert.ui.dialog.ResetProgressDialog
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
            if (it) {
                viewModel.input.resetProgress()
                showTaskCleanDialog()
            }
        }.show()

    }

    override val viewModel: SettingsViewModel by viewModels {
        factory
    }

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("Settings", visible = true, canBack = canBack)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvSettings?.adapter = adapter
        rvSettings?.layoutManager = LinearLayoutManager(context)
        rvSettings?.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            ))
    }

    private fun showTaskCleanDialog(){
        ClearTaskDialog(requireContext()) {
            activity?.also { act ->
                startActivity(Intent(act, SplashActivity::class.java))
                act.finish()
            }

        }.show()
    }

    override fun observeViewModel(output: SettingsViewModelOutput?) {
        output?.settingsOutput?.observe(viewLifecycleOwner, Observer {
            checkSettingsContentOutput(it)
        })
        output?.resetProgressOutput?.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
//                    activity?.also { act ->
//                        startActivity(Intent(act, SplashActivity::class.java))
//                        act.finish()
//                    }
                }
                Status.LOADING -> {
//                    rvSettings.gone()
                }

                Status.ERROR -> {
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

            adapter.setItems(it)
        }

    }

    private fun showVoteDialog() {
        val ratingDialog = RatingDialog.Builder(context)
            .threshold(1f)
            .ratingBarColor(R.color.colorPrimary)
            .playstoreUrl("https://play.google.com/store/apps/details?" +
                    "id=com.meksconway.areyouexpert")
            .build()
        ratingDialog.show()
    }


}
