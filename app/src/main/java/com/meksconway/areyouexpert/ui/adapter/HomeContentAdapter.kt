package com.meksconway.areyouexpert.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.*
import java.lang.IllegalStateException

class HomeContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contentData = arrayListOf<HomeItemType>()


    override fun getItemViewType(position: Int): Int {
        return when (contentData[position].getItemType()) {
            ContentItemType.CATEGORY -> ContentItemType.CATEGORY.ordinal
            ContentItemType.BANNER -> ContentItemType.BANNER.ordinal
            ContentItemType.TITLE -> ContentItemType.TITLE.ordinal
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View

        return when (viewType) {
            ContentItemType.CATEGORY.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_category_view_home_content, parent, false)
                CategoryViewHolder(view)
            }
            ContentItemType.TITLE.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_title_home_content, parent, false)
                TitleViewHolder(view)
            }
            ContentItemType.BANNER.ordinal -> {
                view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_banner, parent, false)
                BannerViewHolder(view)
            }

            else -> throw IllegalStateException()
        }

    }

    override fun getItemCount() = contentData.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = contentData[position]) {
            is HomeBannerModel -> {
                (holder as? BannerViewHolder)?.bind(model)
            }
            is TitleModel -> {
                (holder as? TitleViewHolder)?.bind(model)
            }
            is CategoriesListModel -> {
                (holder as? CategoryViewHolder)?.bind(model)
            }
        }
    }


    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: HomeBannerModel

        fun bind(model: HomeBannerModel) {
            this.model = model

        }
    }

    class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: TitleModel

        private val titleText = itemView.findViewById<TextView>(R.id.txtTitle)

        fun bind(model: TitleModel) {
            this.model = model
            titleText?.text = model.title
        }

    }


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var model: CategoriesListModel

        fun bind(model: CategoriesListModel) {
            this.model = model
        }

    }

}