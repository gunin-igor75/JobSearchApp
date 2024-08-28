package com.github.gunin_igor75.presentation.adapter

import androidx.core.view.isVisible
import com.github.gunin_igor75.common.R
import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.common.base.model.UiVacancyFavorite
import com.github.gunin_igor75.common.databinding.CardItemVacanciesBinding
import com.github.gunin_igor75.presentation.utils.UiVacancyFavoriteDffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class UiVacanciesFavoritesAdapter(
    onClickFavorite: (UiVacancyFavorite) -> Unit,
) : AsyncListDifferDelegationAdapter<ListItem>(UiVacancyFavoriteDffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(
                uiVacancyDelegate(
                    onClickFavorite = onClickFavorite
                )
            )
    }

    private fun uiVacancyDelegate(
        onClickFavorite: (UiVacancyFavorite) -> Unit,
    ) =
        adapterDelegateViewBinding<UiVacancyFavorite, ListItem, CardItemVacanciesBinding>(
            { layoutInflater, container ->
                CardItemVacanciesBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                val resources = binding.root.resources
                with(binding) {
                    textViewViewingCountVacancies.isVisible = item.lookingNumber != null
                    val number = item.lookingNumber ?: 0
                    textViewViewingCountVacancies.text =
                        resources.getQuantityString(R.plurals.people_viewing, number, number)
                    textViewVacanciesTitle.text = item.title
                    textViewVacanciesAddress.text = item.town
                    textViewVacanciesCompany.text = item.company
                    textViewExperience.text = item.previewText
                    textViewPublicationDate.text = item.publishedDate
                    val resDrawable =
                        if (item.isFavorite) {
                            R.drawable.ic_favorite_selected
                        } else R.drawable.ic_favorites
                    imageViewFavorite.setImageResource(resDrawable)
                    imageViewFavorite.setOnClickListener {
                        onClickFavorite(item)
                    }
                }
            }
        }
}