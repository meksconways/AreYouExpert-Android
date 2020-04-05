package com.meksconway.areyouexpert.ui.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.*
import java.lang.IllegalStateException

class HomeContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contentData = arrayListOf<HomeItemType>()

    override fun getItemViewType(position: Int): Int {
        Log.d("***itemType", contentData[position].getItemType().toString())
        return when (contentData[position].getItemType()) {
            ContentItemType.CATEGORY -> ContentItemType.CATEGORY.ordinal
            ContentItemType.BANNER -> ContentItemType.BANNER.ordinal
            ContentItemType.TITLE -> ContentItemType.TITLE.ordinal
        }
    }

    override fun getItemId(position: Int): Long {
        return contentData[position].getItemType().hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view: View
        Log.d("***itemTypeOnCreate", viewType.toString())
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

    override fun getItemCount(): Int {
        Log.d("***listSize", contentData.toString())
        return contentData.size
    }

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

    fun setItems(listItems: List<HomeItemType>?) {
        this.contentData.clear()
        Log.d("***listItems", listItems.toString())
        if (listItems != null) {
            Log.d("***listItems", "not null")
            this.contentData.addAll(listItems)
        }
        Log.d("***listItems", "notify")
        notifyDataSetChanged()
    }


    class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val rvBanner = itemView.findViewById<RecyclerView>(R.id.rvBanner)
        private val mLayoutManager =
            LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        private val snapHelper: PagerSnapHelper by lazy {
            PagerSnapHelper()
        }
        private val mAdapter: BannerAdapter by lazy {
            BannerAdapter()
        }

        fun bind(model: HomeBannerModel) {
            if (rvBanner.layoutManager == null) {
                rvBanner.layoutManager = mLayoutManager
            }
            snapHelper.attachToRecyclerView(rvBanner)
            rvBanner.adapter = mAdapter
            rvBanner.setItemViewCacheSize(2)
            rvBanner.setHasFixedSize(true)
            mAdapter.setItems(model.banner)

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
        private val titleText = itemView.findViewById<TextView>(R.id.txtCatTitle)
        private val catImage = itemView.findViewById<ImageView>(R.id.imgCategory)

        fun bind(model: CategoriesListModel) {
            Log.d("***categoryVH", model.toString())
            this.model = model
            Glide.with(itemView)
                .asBitmap()
                .load(model.resources.drawableRes)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }

                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        catImage.setImageBitmap(resource)
                    }

                })
            titleText?.text = model.name
        }

    }

}