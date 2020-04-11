package com.meksconway.areyouexpert.ui.fragment.home

import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.CategoryModel
import com.meksconway.areyouexpert.domain.usecase.ContentItemType.*
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.adapter.HomeContentAdapter
import com.meksconway.areyouexpert.ui.fragment.categoryonbard.CategoryOnBoardFragment
import com.meksconway.areyouexpert.ui.fragment.categoryonbard.CategoryOnBoardViewModel
import com.meksconway.areyouexpert.ui.fragment.notification.NotificationFragment
import com.meksconway.areyouexpert.ui.fragment.settings.SettingsFragment
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import com.meksconway.areyouexpert.util.ToolbarConfigration
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    override val layRes: Int
        get() = R.layout.home_fragment

    private val categoryOnBoardViewModel: CategoryOnBoardViewModel by activityViewModels {
        factory
    }

    private val adapter: HomeContentAdapter by lazy {
        HomeContentAdapter {
            when (it.getItemType()) {
                CATEGORY -> {
                    categoryOnBoardViewModel.input.getContent(it as CategoryModel)
                    categoryOnBoardViewModel.input.setButtonColor(it.resources.primaryColor)
                    navigator?.start(CategoryOnBoardFragment())
                }
                BANNER -> {}
                TITLE -> {}
            }
        }
    }

    override val viewModel: HomeViewModel by viewModels {
        factory
    }

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            checkHomeContentOutput(it)
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvHome?.setItemViewCacheSize(30)
        rvHome?.setHasFixedSize(true)
        rvHome?.layoutManager = GridLayoutManager(context, 2)
        rvHome?.adapter = adapter
    }


    private fun checkHomeContentOutput(resource: Res<HomeContentModel>) {
        when (resource.status) {
            Status.SUCCESS -> {
                // set Adapter
                hideProgress()
                setAdapter(resource.data)
            }

            Status.ERROR -> {
                //show errors
                hideProgress()
            }

            Status.LOADING -> {
                //show progress
                showProgress()
            }
        }
    }

    private fun hideProgress() {
        progressBar.gone()
        rvHome.visible()
    }

    private fun showProgress() {
        progressBar.visible()
        rvHome.gone()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        return inflater.inflate(R.menu.menu_home_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                navigator?.start(SettingsFragment().apply { canBack = true })
                true
            }
            R.id.profile -> {
                false
            }
            R.id.notification -> {
                navigator?.start(NotificationFragment().apply { canBack = true })
                true
            }
            else -> false
        }
    }

    private fun setAdapter(listItems: HomeContentModel?) {
        listItems?.content?.let { list ->
            (rvHome.layoutManager as? GridLayoutManager)?.apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (list[position].getItemType()) {
                            BANNER -> 2
                            CATEGORY -> 1
                            TITLE -> 2
                        }
                    }
                }
            }
            adapter.setItems(list)
        }

    }

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("Are You Expert", true, canBack = false)
    }


}
