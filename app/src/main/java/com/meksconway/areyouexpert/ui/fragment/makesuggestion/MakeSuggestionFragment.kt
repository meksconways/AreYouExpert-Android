package com.meksconway.areyouexpert.ui.fragment.makesuggestion

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.jakewharton.rxbinding3.widget.textChanges
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.util.ToolbarConfigration
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.make_suggestion_fragment.*

class MakeSuggestionFragment :
    BaseFragment<MakeSuggestionViewModelInput, MakeSuggestionViewModelOutput,
            MakeSuggestionViewModel>() {


    private val compositeDisposable = CompositeDisposable()

    override val viewModel: MakeSuggestionViewModel by viewModels {
        factory
    }
    override val layRes: Int
        get() = R.layout.make_suggestion_fragment

    override fun setToolbarConfig(): ToolbarConfigration {
        return ToolbarConfigration("Make Suggestion", true, canBack = true)
    }

    override fun observeViewModel(output: MakeSuggestionViewModelOutput?) {
        output?.suggestionStringOutput?.observe(viewLifecycleOwner, Observer {
            btnSend.isEnabled = it.isNullOrBlank().not()
        })
    }

    override fun onDestroyView() {
        compositeDisposable.clear()
        super.onDestroyView()
    }


    override fun viewDidLoad() {
        super.viewDidLoad()

        txtInputMakeSuggestion.textChanges()
            .map { it.toString() }
            .subscribe {
                viewModel.input.sendSuggestion(it)
            }.addTo(compositeDisposable)

    }



}
