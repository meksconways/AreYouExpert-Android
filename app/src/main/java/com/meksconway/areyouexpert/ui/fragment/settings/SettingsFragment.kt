package com.meksconway.areyouexpert.ui.fragment.settings

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.ui.adapter.SettingsAdapter
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import kotlinx.android.synthetic.main.settings_fragment.*

abstract class SettingsFragment : BaseFragment<SettingsViewModelInput, SettingsViewModelOutput
        , SettingsViewModel>() {

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
        })
        viewModel._data.observe(viewLifecycleOwner, Observer {
            checkSettingsContentOutput(it)
        })
    }

    private fun checkSettingsContentOutput(resource: Res<SettingsViewModel>) {
        when (resource.status) {
            Status.SUCCESS -> {
                setAdapter(resource.data)
                Toast.makeText(context,"veri geldi", Toast.LENGTH_SHORT).show()
            }
            Status.ERROR -> {
                Toast.makeText(context,resource.error?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                Toast.makeText(context,"loading",Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun setAdapter(model: SettingsViewModel?){
        adapter.setHasStableIds(true)
        model?.let {
            rvSettings?.adapter = adapter
            rvSettings?.layoutManager = layoutManager
        }
    }
}
