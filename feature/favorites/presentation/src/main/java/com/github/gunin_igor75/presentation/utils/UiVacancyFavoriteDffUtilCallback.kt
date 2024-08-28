package com.github.gunin_igor75.presentation.utils

import com.github.gunin_igor75.common.base.base.BaseDiffUtilItemCallback
import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.common.base.model.UiVacancyFavorite

object UiVacancyFavoriteDffUtilCallback: BaseDiffUtilItemCallback(){

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        if (oldItem is UiVacancyFavorite && newItem is UiVacancyFavorite) {
            return oldItem == newItem
        }
        return super.areContentsTheSame(oldItem, newItem)
    }
}