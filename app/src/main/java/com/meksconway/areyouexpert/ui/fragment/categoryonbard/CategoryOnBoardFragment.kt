package com.meksconway.areyouexpert.ui.fragment.categoryonbard

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItem
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.extension.viewextension.visible
import com.meksconway.areyouexpert.ui.adapter.CategoryOnBoardAdapter
import com.meksconway.areyouexpert.ui.fragment.quiz.QuizFragment
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status.*
import com.meksconway.areyouexpert.util.ToolbarConfigration
import kotlinx.android.synthetic.main.category_on_board_fragment.*

class CategoryOnBoardFragment :
    BaseFragment<CategoryOnBoardInput, CategoryOnBoardOutput, CategoryOnBoardViewModel>() {


    companion object {
        fun newInstance() = CategoryOnBoardFragment()
    }

    override val viewModel: CategoryOnBoardViewModel by activityViewModels {
        factory
    }

    private val adapter: CategoryOnBoardAdapter by lazy {
        CategoryOnBoardAdapter()
    }

    override val layRes: Int
        get() = R.layout.category_on_board_fragment

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("", false)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        rvCategoryOnBoard.layoutManager = LinearLayoutManager(context)
        rvCategoryOnBoard.adapter = adapter
        rvCategoryOnBoard.setItemViewCacheSize(8)
        btnStart.setOnClickListener {
            navigator?.start(QuizFragment())
        }
    }

    override fun observeViewModel(output: CategoryOnBoardOutput?) {
        output?.contentOutput?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.status.toString(), Toast.LENGTH_SHORT).show()
            checkOutput(it)
        })

        output?.buttonColorOutput?.observe(viewLifecycleOwner, Observer {
            btnStart.backgroundTintList = ContextCompat.getColorStateList(requireContext(), it)
        })
    }

    private fun checkOutput(resource: Res<List<CategoryOnBoardItem>>) {
        when (resource.status) {
            SUCCESS -> {
                progressBar.gone()
                rvCategoryOnBoard.visible()
                setAdapter(resource.data)
            }
            LOADING -> {
                progressBar.visible()
                rvCategoryOnBoard.gone()
            }
            ERROR -> {
                progressBar.gone()
                rvCategoryOnBoard.gone()
            }
        }
    }

    private fun setAdapter(data: List<CategoryOnBoardItem>?) {
        adapter.setItems(data)
    }


}
