package com.meksconway.areyouexpert.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.meksconway.areyouexpert.domain.usecase.HomeContentModel
import com.meksconway.areyouexpert.domain.usecase.HomeItemType

class HomeContentAdapterDiffUtil(
    private val oldList: List<HomeItemType>,
    private val newList: List<HomeItemType>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].getContentId() == newList[newItemPosition].getContentId()


    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}