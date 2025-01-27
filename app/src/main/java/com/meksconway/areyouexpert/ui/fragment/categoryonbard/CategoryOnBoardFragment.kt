package com.meksconway.areyouexpert.ui.fragment.categoryonbard

import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.domain.usecase.CategoryModel
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItem
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

    override val viewModel: CategoryOnBoardViewModel by activityViewModels() {
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

    private lateinit var _category: CategoryModel

    override fun viewDidLoad() {
        super.viewDidLoad()
        arguments?.let {
            it.getParcelable<CategoryModel>("category")?.let { categoryModel ->
                //viewModel.input.getContent(categoryModel)
            }
        }
        rvCategoryOnBoard.layoutManager = LinearLayoutManager(context)
        rvCategoryOnBoard.adapter = adapter
        rvCategoryOnBoard.setItemViewCacheSize(8)
        btnStart.setOnClickListener {
            navigator?.start(QuizFragment.newInstance(_category))
        }
    }

    override fun observeViewModel(output: CategoryOnBoardOutput?) {
        output?.contentOutput?.observe(viewLifecycleOwner, Observer {
            checkOutput(it)
        })

        output?.categoryOutput?.observe(viewLifecycleOwner, Observer {
            _category = it
        })

        output?.buttonColorOutput?.observe(viewLifecycleOwner, Observer {
            btnStart.backgroundTintList = ContextCompat.getColorStateList(requireContext(), it)
        })
    }

    private fun checkOutput(resource: Res<List<CategoryOnBoardItem>>) {
        when (resource.status) {
            SUCCESS -> {
                hideLoading()
                setAdapter(resource.data)

            }
            LOADING -> {
                showLoading()
            }
            ERROR -> {
                hideLoading()
            }
        }
    }


    private fun setAdapter(data: List<CategoryOnBoardItem>?) {
        adapter.setItems(data)
    }


}
