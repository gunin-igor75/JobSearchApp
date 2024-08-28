package com.github.gunin_igor75.presentation.adapter

import androidx.core.view.isVisible
import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.databinding.CardItemVacanciesBinding
import com.github.gunin_igor75.presentation.model.UiVacancy
import com.github.gunin_igor75.presentation.utils.UiVacancyDffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class UiVacancyAdapter(
    onClickItem: (UiVacancy) -> Unit,
    onClickFavorite: (UiVacancy) -> Unit
) : AsyncListDifferDelegationAdapter<ListItem>(UiVacancyDffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(uiVacancyDelegate(
                onClickItem = onClickItem,
                onClickFavorite = onClickFavorite
            ))
    }

    private fun uiVacancyDelegate(
        onClickItem: (UiVacancy) -> Unit,
        onClickFavorite: (UiVacancy) -> Unit
    ) =
        adapterDelegateViewBinding<UiVacancy, ListItem, CardItemVacanciesBinding>(
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
                    textViewExperience.text = item.experiens
                    textViewPublicationDate.text = item.publishedDate
                    val resDrawable =
                        if (item.isFavorite) {
                            com.github.gunin_igor75.common.R.drawable.ic_favorite_selected
                        } else com.github.gunin_igor75.common.R.drawable.ic_favorites
                    imageViewFavorite.setImageResource(resDrawable)
                    imageViewFavorite.setOnClickListener {
                        onClickFavorite(item)
                    }
                    root.setOnClickListener {
                        onClickItem(item)
                    }
                }
            }
        }
}