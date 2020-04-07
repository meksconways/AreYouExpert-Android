package com.meksconway.areyouexpert.ui.fragment.notification

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment

class NotificationFragment :
    BaseFragment<NotificationViewModelInput, NotificationViewModelOutput, NotificationViewModel>() {
    override val viewModel: NotificationViewModel by viewModels {
        factory
    }
    override val layRes: Int
        get() = R.layout.notification_fragment

    override fun observeViewModel(output: NotificationViewModelOutput?) {
        output?.notificationData?.observe(viewLifecycleOwner, Observer{
            Log.d("***data",it.status.toString())
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.getNotifications()
    }
}
