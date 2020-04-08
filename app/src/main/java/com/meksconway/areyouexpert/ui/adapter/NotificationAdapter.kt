package com.meksconway.areyouexpert.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    private val notificationData = arrayListOf<NotificationEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return notificationData[position].nofiticationId.toLong()
    }

    override fun getItemCount() = notificationData.size

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationData[position])
    }

    fun setItems(list: List<NotificationEntity>) {
        notificationData.clear()
        notificationData.addAll(list)
        notifyDataSetChanged()
    }

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var model: NotificationEntity
        private val notificationTitle = itemView.findViewById<TextView>(R.id.txtNotificationTitle)
        private val notificationMessage = itemView.findViewById<TextView>(R.id.txtNotificationMessage)
        fun bind(model: NotificationEntity) {
            this.model = model
            notificationTitle?.text = model.notificationTitle
            notificationMessage?.text = model.notificationMessage
        }
    }
}