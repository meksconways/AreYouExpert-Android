package com.meksconway.areyouexpert.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.meksconway.areyouexpert.ui.activity.main.MainAcitivityViewModel
import com.meksconway.areyouexpert.ui.activity.main.MainActivity
import com.meksconway.areyouexpert.util.ToolbarConfigration
import com.meksconway.areyouexpert.viewmodel.BaseViewModel
import com.meksconway.areyouexpert.viewmodel.Input
import com.meksconway.areyouexpert.viewmodel.Output
import com.meksconway.areyouexpert.viewmodel.ViewModelFactory
import com.trendyol.medusalib.navigator.MultipleStackNavigator
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<I : Input, O : Output, VM : BaseViewModel<I, O>> : DaggerFragment() {

    @Inject
    lateinit var factory: ViewModelFactory
    abstract val viewModel: VM
    abstract val layRes: Int
    var navigator: MultipleStackNavigator? = null
    private val mainVM: MainAcitivityViewModel by activityViewModels {
        factory
    }

    companion object {
        var canBack: Boolean = false
    }

    abstract fun setToolbarConfig(): ToolbarConfigration

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        initStackNavigator(context)
    }

    private fun initStackNavigator(context: Context?) {
        if (context is MainActivity && navigator == null) {
            navigator = context.multipleStackNavigator
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initStackNavigator(context)
        setHasOptionsMenu(true)
        mainVM.input.setToolbarConfig(setToolbarConfig())
        viewDidLoad()
        observeViewModel(viewModel.output)

    }

//    abstract fun createViewModel()

    abstract fun observeViewModel(output: O?)
    open fun viewDidLoad() {}

}