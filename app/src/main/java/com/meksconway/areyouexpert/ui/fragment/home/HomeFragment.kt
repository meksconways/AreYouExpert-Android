package com.meksconway.areyouexpert.ui.fragment.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.ContentItemType
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.adapter.HomeContentAdapter
import com.meksconway.areyouexpert.ui.fragment.notification.NotificationFragment
import com.meksconway.areyouexpert.ui.fragment.settings.SettingsFragment
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    override val layRes: Int
        get() = R.layout.home_fragment

    private val adapter = HomeContentAdapter()

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
        setHasOptionsMenu(true);
        rvHome?.setItemViewCacheSize(20)
        rvHome?.setHasFixedSize(true)
        rvHome?.adapter = adapter
        toolbar.inflateMenu(R.menu.menu_home_fragment)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.notification -> {
                    navigator?.start(NotificationFragment())
                    return@setOnMenuItemClickListener true
                }
                R.id.profile -> {
//                    multipleStackNavigator?.start(NotificationFragment())
                    return@setOnMenuItemClickListener true
                }
                R.id.settings -> {
                    navigator?.start(SettingsFragment())
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
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

    private fun setAdapter(listItems: HomeContentModel?) {
        listItems?.content?.let { list ->
            rvHome?.layoutManager = GridLayoutManager(context, 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (list[position].getItemType()) {
                            ContentItemType.BANNER -> 2
                            ContentItemType.CATEGORY -> 1
                            ContentItemType.TITLE -> 2
                        }
                    }
                }
            }
            adapter.setItems(list)
        }

    }


}
