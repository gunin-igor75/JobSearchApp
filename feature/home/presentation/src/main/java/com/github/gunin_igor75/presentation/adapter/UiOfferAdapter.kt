package com.github.gunin_igor75.presentation.adapter

import androidx.core.view.isVisible
import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.common.base.model.UiOffer
import com.github.gunin_igor75.presentation.databinding.CardItemOfferBinding
import com.github.gunin_igor75.presentation.utils.UiOfferDffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class UiOfferAdapter(
    followToLink: (String) -> Unit,
    raiseResume: () -> Unit,
) : AsyncListDifferDelegationAdapter<ListItem>(UiOfferDffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(
                uiOfferDelegate(
                    followToLink = followToLink,
                    raiseResume = raiseResume
                )
            )
    }

    private fun uiOfferDelegate(
        followToLink: (String) -> Unit,
        raiseResume: () -> Unit,
    ) =
        adapterDelegateViewBinding<UiOffer, ListItem, CardItemOfferBinding>(
            { layoutInflater, container ->
                CardItemOfferBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                with(binding) {
                    item.icon?.let { imageViewIcon.setImageResource(it) }
                    imageViewIcon.isVisible = item.icon != null
                    textViewButton.isVisible = item.buttonText != null
                    textViewTitle.maxLines = if (item.buttonText == null) MAX_LINE else MIN_LINE
                    textViewButton.text = item.buttonText
                    textViewButton.setOnClickListener {
                        raiseResume()
                    }
                    textViewTitle.text = item.title
                    root.setOnClickListener {
                        followToLink(item.link)
                    }
                }
            }
        }

    private companion object {
        const val MAX_LINE = 3
        const val MIN_LINE = 2
    }
}