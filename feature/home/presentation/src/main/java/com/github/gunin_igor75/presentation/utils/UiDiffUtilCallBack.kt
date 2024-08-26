package com.github.gunin_igor75.presentation.utils

import com.github.gunin_igor75.common.base.base.BaseDiffUtilItemCallback
import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.presentation.model.UiOffer

object UiOfferDffUtilCallback : BaseDiffUtilItemCallback() {
    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiOffer && newItem is UiOffer) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}