package com.meksconway.areyouexpert.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.*

class HomeContentAdapter
constructor(private val callback: ((HomeItemType) -> Unit)? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contentData = arrayListOf<HomeItemType>()

    override fun getItemViewType(position: Int): Int {
        return when (contentData[position].getItemType()) {
            ContentItemType.CATEGORY -> ContentItemType.CATEGORY.ordinal
            ContentItemType.BANNER -> ContentItemType.BANNER.ordinal
            ContentItemType.TITLE -> ContentItemType.TITLE.ordinal
        }
    }

    override fun getItemId(position: Int): Long {
        return contentData[position].getContentId().toLong()
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

    override fun getItemCount(): Int {
        Log.d("***listSize", contentData.toString())
        return contentData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = contentData[position]) {
            is HomeBannerModel -> {
                (holder as? BannerViewHolder)?.bind(model)
                holder.itemView.setOnClickListener {
                    callback?.invoke(model)
                }
            }
            is TitleModel -> {
                (holder as? TitleViewHolder)?.bind(model)
                holder.itemView.setOnClickListener {
                    callback?.invoke(model)
                }
            }
            is CategoryModel -> {
                (holder as? CategoryViewHolder)?.bind(model)
                holder.itemView.setOnClickListener {
                    callback?.invoke(model)
                }
            }
        }
    }

    fun setItems(listItems: List<HomeItemType>?) {
        contentData.clear()
        if (listItems != null) {
//            val diffCallback = HomeContentAdapterDiffUtil(contentData, listItems)
//            val diffResult = DiffUtil.calculateDiff(diffCallback)
            contentData.addAll(listItems)
            notifyDataSetChanged()
//            diffResult.dispatchUpdatesTo(this)
        }
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

        lateinit var model: CategoryModel
        private val titleText = itemView.findViewById<TextView>(R.id.txtCatTitle)
        private val catImage = itemView.findViewById<ImageView>(R.id.imgCategory)
        private val catBackground = itemView.findViewById<ImageView>(R.id.imgCategoryBackground)

        fun bind(model: CategoryModel) {
            Log.d("***categoryVH", model.toString())
            this.model = model
            catImage.setColorFilter(
                ContextCompat.getColor(itemView.context, model.resources.darkColor),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            catBackground.setColorFilter(
                ContextCompat.getColor(itemView.context, model.resources.lightColor),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            catImage.setImageResource(model.resources.drawableRes)
            titleText?.text = model.name
        }

    }

}