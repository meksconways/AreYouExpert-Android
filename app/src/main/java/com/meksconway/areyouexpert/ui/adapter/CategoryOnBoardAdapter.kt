package com.meksconway.areyouexpert.ui.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.meksconway.areyouexpert.R
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardHeaderModel
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItem
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItemType.HEADER
import com.meksconway.areyouexpert.domain.usecase.CategoryOnBoardItemType.PROGRESS
import com.meksconway.areyouexpert.domain.usecase.CategoryProgressModel

class CategoryOnBoardAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val contentItems = arrayListOf<CategoryOnBoardItem>()

    override fun getItemId(position: Int): Long {
        return contentItems[position].getId().toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return when (contentItems[position].getItemType()) {
            HEADER -> HEADER.ordinal
            PROGRESS -> PROGRESS.ordinal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        return when (viewType) {

            HEADER.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_onboard_header,parent,false)
                CategoryOnBoardHeaderVH(view)
            }

            PROGRESS.ordinal -> {
                view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_onboard_progress,parent,false)
                CategoryOnBoardProgressVH(view)
            }

            else -> throw IllegalStateException()
        }


    }

    override fun getItemCount() = contentItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val model = contentItems[position]) {
            is CategoryOnBoardHeaderModel -> {
                (holder as? CategoryOnBoardHeaderVH)?.bind(model)
            }
            is CategoryProgressModel -> {
                (holder as? CategoryOnBoardProgressVH)?.bind(model)
            }
        }
    }

    fun setItems(data: List<CategoryOnBoardItem>?) {
        contentItems.clear()
        data?.let {
            contentItems.addAll(data)
        }
        notifyDataSetChanged()
    }


    class CategoryOnBoardHeaderVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val headerImage = itemView.findViewById<ImageView>(R.id.imgCatOnBoardImage)
        private val headerText = itemView.findViewById<TextView>(R.id.txtCatOnBoardTitle)

        fun bind(model: CategoryOnBoardHeaderModel) {
            headerImage.setImageResource(model.model.resources.drawableRes)
            headerText?.text = model.model.name
//            headerImage?.setColorFilter(ContextCompat.getColor(itemView.context,
//                model.model.resources.primaryColor))
//            headerImage?.alpha = 0.5f
        }

    }

    class CategoryOnBoardProgressVH(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val progressText = itemView.findViewById<TextView>(R.id.txtCategoryOnBoardProgressTitle)
        private val progressView = itemView.findViewById<RoundCornerProgressBar>(R.id.prCategoryOnProgress)

        @SuppressLint("SetTextI18n")
        fun bind(model: CategoryProgressModel) {
            Log.d("***model",model.toString())
            progressText?.text = model.getPercent()
            progressView?.post {
                progressView.max = 10f
                progressView.progress = model.progress.toFloat()
                progressView.progressColor = ContextCompat.getColor(itemView.context, model.content.resources.primaryColor)
                progressView.secondaryProgress = 10f
                progressView.secondaryProgressColor = ContextCompat.getColor(itemView.context, model.content.resources.lightColor)
                progressView.animate()
                progressView.requestLayout()
            }


        }

    }

}