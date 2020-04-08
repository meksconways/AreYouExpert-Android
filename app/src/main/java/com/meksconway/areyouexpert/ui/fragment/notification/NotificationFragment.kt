package com.meksconway.areyouexpert.ui.fragment.notification

import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.base.BaseFragment
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity
import com.meksconway.areyouexpert.ui.adapter.NotificationAdapter
import com.meksconway.areyouexpert.util.Res
import com.meksconway.areyouexpert.util.Status
import com.meksconway.areyouexpert.viewmodel.SingleLiveEvent
import io.reactivex.Notification
import kotlinx.android.synthetic.main.notification_fragment.*
import java.util.*

class NotificationFragment :
    BaseFragment<NotificationViewModelInput, NotificationViewModelOutput, NotificationViewModel>() {

    override val viewModel: NotificationViewModel by viewModels {
        factory
    }
    override val layRes: Int
        get() = R.layout.notification_fragment

    private val adapter = NotificationAdapter()

    override fun observeViewModel(output: NotificationViewModelOutput?) {
        output?.notificationData?.observe(viewLifecycleOwner, Observer {
            chechNotificationContentOutput(it)
            Log.d("***data", it.status.toString())
        })
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        viewModel.input.getNotifications()
    }

    private fun chechNotificationContentOutput(resource: Res<List<NotificationEntity>>) {
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

    private fun setAdapter(list: List<NotificationEntity>?){
        adapter.setHasStableIds(true)
        list?.let {list ->
            val layoutManager = LinearLayoutManager(context)
            recycler_view?.adapter = adapter
            recycler_view?.layoutManager = layoutManager
            adapter.setItems(list)
        }
    }

}

