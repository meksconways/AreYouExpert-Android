package com.meksconway.areyouexpert.ui.fragment.home

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.ContentItemType
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.enums.Resource
import com.meksconway.areyouexpert.ui.adapter.HomeContentAdapter
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModelInput, HomeViewModelOutput, HomeViewModel>() {

    override val layRes: Int
        get() = R.layout.home_fragment

    private val adapter = HomeContentAdapter()
    val pool = RecyclerView.RecycledViewPool()


    override fun viewDidLoad() {
        super.viewDidLoad()
    }

    override val viewModel: HomeViewModel by viewModels {
        factory
    }

    override fun observeViewModel(output: HomeViewModelOutput?) {
        output?.homeContentOutput?.observe(viewLifecycleOwner, Observer {
            //            checkHomeContentOutput(it)
        })
        viewModel._data.observe(viewLifecycleOwner, Observer {
            checkHomeContentOutput(it)
            Log.d("***data", it.data.toString())
        })

    }


    private fun checkHomeContentOutput(resource: Res<HomeContentModel>) {
        when (resource.status) {
            Status.SUCCESS -> {
                // set Adapter
                setAdapter(resource.data)
                Toast.makeText(context, "veri geldi", Toast.LENGTH_SHORT).show()
            }

            Status.ERROR -> {
                //show errors
                Toast.makeText(context, resource.error?.localizedMessage, Toast.LENGTH_SHORT).show()
            }

            Status.LOADING -> {
                //show progress
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(listItems: HomeContentModel?) {
        adapter.setHasStableIds(true)
        listItems?.content?.let { list ->
            val layoutManager = GridLayoutManager(context, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (list[position].getItemType()) {
                        ContentItemType.BANNER -> 2
                        ContentItemType.CATEGORY -> 1
                        ContentItemType.TITLE -> 2
                    }
                }
            }
//            rvHome?.apply {
//                setItemViewCacheSize(15)
//                setHasFixedSize(true)
//                this.layoutManager = layoutManager
//                adapter = adapter
//
//            }
            rvHome.setRecycledViewPool(pool)
            rvHome?.setItemViewCacheSize(12)
            rvHome?.setHasFixedSize(true)
            rvHome?.adapter = adapter
            rvHome?.layoutManager = layoutManager
            adapter.setItems(list)
        }

    }


}
