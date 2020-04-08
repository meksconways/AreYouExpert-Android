package com.meksconway.areyouexpert.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.data.service.local.entity.NotificationEntity

class NotificationAdapter : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    val notificationData = arrayListOf<NotificationEntity>()

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var model: NotificationEntity
        fun bind(model: NotificationEntity) {
            this.model = model
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_list, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notificationData.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bind(notificationData[position])
    }
    fun setItems(list: List<NotificationEntity> ) {
        notificationData.clear()
        notificationData.addAll(list)
        notifyDataSetChanged()
    }
}