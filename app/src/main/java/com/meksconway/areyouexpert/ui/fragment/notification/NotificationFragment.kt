package com.meksconway.areyouexpert.ui.fragment.notification

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.ui.adapter.NotificationAdapter
import com.meksconway.areyouexpert.ui.fragment.home.HomeViewModel
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import kotlinx.android.synthetic.main.notification_fragment.*

class NotificationFragment :
    BaseFragment<NotificationViewModelInput, NotificationViewModelOutput, NotificationViewModel>() {

    override val viewModel: NotificationViewModel by viewModels {
        factory
    }
    override val layRes: Int
        get() = R.layout.notification_fragment

    val aViewModel: HomeViewModel by viewModels {
        factory
    }

    private val adapter = NotificationAdapter()
    private val layoutManager = LinearLayoutManager(context)

    override fun observeViewModel(output: NotificationViewModelOutput?) {
        output?.notificationData?.observe(viewLifecycleOwner, Observer {
            checkNotificationContentOutput(it)
            Log.d("***data", it.status.toString())
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        aViewModel.input.getHomeContent()

    }

    private fun checkNotificationContentOutput(resource: Res<List<NotificationEntity>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                //set Adapter
                setAdapter(resource.data)
                Toast.makeText(context, "veri geldi", Toast.LENGTH_SHORT).show()
            }
            Status.ERROR -> {
                //set errors
                Toast.makeText(context, resource.error?.localizedMessage, Toast.LENGTH_SHORT).show()
            }
            Status.LOADING -> {
                Toast.makeText(context, "loading", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(model: List<NotificationEntity>?) {
        adapter.setHasStableIds(true)
        model?.let { list ->
            rvNotification?.adapter = adapter
            rvNotification?.layoutManager = layoutManager
            adapter.setItems(list)
        }
    }

}

