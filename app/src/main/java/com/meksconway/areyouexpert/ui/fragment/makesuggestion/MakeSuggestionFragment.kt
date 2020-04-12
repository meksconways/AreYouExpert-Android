package com.meksconway.areyouexpert.ui.fragment.makesuggestion

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.extension.viewextension.gone
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import com.meksconway.areyouexpert.util.ToolbarConfigration
import kotlinx.android.synthetic.main.home_fragment.*

class MakeSuggestionFragment :
    BaseFragment<MakeSuggestionViewModelInput, MakeSuggestionViewModelOutput,
            MakeSuggestionViewModel>() {


    override val viewModel: MakeSuggestionViewModel by viewModels {
        factory
    }
    override val layRes: Int
        get() = R.layout.make_suggestion_fragment

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("Make Suggestion", true, canBack = true)
    }

    override fun observeViewModel(output: MakeSuggestionViewModelOutput?) {
        output?.sendData?.observe(viewLifecycleOwner, Observer {
            checkMakeSuggestionContentOutput(it)
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
    }

    private fun checkMakeSuggestionContentOutput(resource: Res<String>) {
        when (resource.status) {
            Status.SUCCESS -> {

            }
            Status.ERROR -> {

            }
            Status.LOADING -> {

            }
        }
    }
    private fun hideProgress(){
        progressBar.gone()

    }

}
