package com.meksconway.areyouexpert.ui.fragment.home

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.ContentItemType
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.enums.Resource
import com.meksconway.areyouexpert.ui.adapter.HomeContentAdapter
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    override val layRes: Int
        get() = R.layout.home_fragment

    private val adapter = HomeContentAdapter()

    override fun viewDidLoad() {
        super.viewDidLoad()
        adapter.setHasStableIds(true)
    }

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            checkHomeContentOutput(it)
        })
        viewModel?.input?.getHomeContent()
    }

    private fun checkHomeContentOutput(resource: Resource<HomeContentModel>) {
        when (resource) {
            is Resource.Success -> {
                // set Adapter
                setAdapter(resource.data)
                Toast.makeText(context, "veri geldi", Toast.LENGTH_SHORT).show()
            }

            is Resource.Error -> {
                //show errors
                Toast.makeText(context, resource.message, Toast.LENGTH_SHORT).show()
            }

            is Resource.Loading -> {
                //show progress
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(listItems: HomeContentModel?) {

        listItems?.content?.let { list ->
            val layoutManager = GridLayoutManager(context,3)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (list[position].getItemType()) {
                        ContentItemType.BANNER -> 3
                        ContentItemType.CATEGORY -> 1
                        ContentItemType.TITLE-> 3
                    }
                }
            }

            rvHome.recycledViewPool.setMaxRecycledViews(3, 20)
            rvHome?.setItemViewCacheSize(12)
            rvHome?.setHasFixedSize(true)
            rvHome?.adapter = adapter
            rvHome?.layoutManager = layoutManager
            adapter.setItems(list)
        }

    }

    override fun createViewModel(): HomeViewModel {
        return ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }


}
