package com.meksconway.areyouexpert.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.HomeBannerListModel

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.BannerItemViewHolder>() {


    private val bannerItems = arrayListOf<HomeBannerListModel>()

    fun setItems(list: List<HomeBannerListModel>?) {
        bannerItems.clear()
        if (list != null){
            bannerItems.addAll(list)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner_content, parent, false)
        return BannerItemViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return bannerItems[position].bannerId.toLong()
    }

    override fun getItemCount() = bannerItems.size

    override fun onBindViewHolder(holder: BannerItemViewHolder, position: Int) {
        holder.bind(bannerItems[position])
    }


    class BannerItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: HomeBannerListModel
        private val titleText = itemView.findViewById<TextView>(R.id.txtBannerTitle)
        private val bannerGradientImage = itemView.findViewById<ImageView>(R.id.imgBannerGradient)
        private val bannerImage = itemView.findViewById<ImageView>(R.id.imgBannerImage)

        fun bind(model: HomeBannerListModel) {

            this.model = model
            titleText?.text = model.bannerTitle

            Glide.with(itemView)
                .load(model.bannerGradient)
                .into(bannerGradientImage)

            Glide.with(itemView)
                .load(model.bannerImage)
                .into(bannerImage)
        }

    }


}