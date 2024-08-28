package com.github.gunin_igor75.common.base.base

import androidx.recyclerview.widget.DiffUtil
import com.github.gunin_igor75.common.base.model.ListItem

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.listItemId == newItem.listItemId
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return oldItem.listItemId == newItem.listItemId
    }
}