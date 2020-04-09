package com.meksconway.areyouexpert.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.SettingsListModel


class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private val settingsData = arrayListOf<SettingsListModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_settings, parent, false)
        return SettingsViewHolder(view)
    }

    override fun getItemCount() = settingsData.size

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.bind(settingsData[position])
    }
    fun setItems(list: SettingsListModel){
        settingsData.clear()
        settingsData.add(list)
        notifyDataSetChanged()
    }
    //recyclerView içerisindeki itemi tutuyor...
    class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var model: SettingsListModel
        private val settingsTitle = itemView.findViewById<TextView>(R.id.txtSettingsTitle)
        fun bind(model: SettingsListModel) {
            this.model = model
            settingsTitle?.text = model.settingsTitle
        }
    }


}