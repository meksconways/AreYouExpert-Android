package com.meksconway.areyouexpert.ui.fragment.settings

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.SettingsModel
import com.meksconway.areyouexpert.ui.adapter.SettingsAdapter
import com.meksconway.areyouexpert.util.Res
import kotlinx.android.synthetic.main.settings_fragment.*

abstract class SettingsFragment :
    BaseFragment<SettingsViewModelInput, SettingsViewModelOutput, SettingsViewModel>() {

    override val layRes: Int
        get() = R.layout.settings_fragment

    private val adapter = SettingsAdapter()
    private val layoutManager = LinearLayoutManager(context)

    override val viewModel: SettingsViewModel by viewModels {
        factory
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
        list?.let {
            rvSettings?.adapter = adapter
            rvSettings?.layoutManager = layoutManager
            adapter.setItems(it)
        }

    }
}
