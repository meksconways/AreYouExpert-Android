package com.meksconway.areyouexpert.ui.fragment.settings

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.SettingsModel
import com.meksconway.areyouexpert.ui.adapter.SettingsAdapter
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.ToolbarConfigration
import kotlinx.android.synthetic.main.settings_fragment.*

class SettingsFragment :
    BaseFragment<SettingsViewModelInput, SettingsViewModelOutput, SettingsViewModel>() {

    override val layRes: Int
        get() = R.layout.settings_fragment

    private val adapter = SettingsAdapter()
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
    }

    private fun checkSettingsContentOutput(resource: Res<List<SettingsModel>>) {
        if (resource.data != null) {
            setAdapter(resource.data)
        }
    }

    private fun setAdapter(list: List<SettingsModel>?) {
        adapter.setHasStableIds(true)
        rvSettings?.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        list?.let {
            rvSettings?.adapter = adapter
            rvSettings?.layoutManager = layoutManager
            adapter.setItems(it)
        }

    }
}
