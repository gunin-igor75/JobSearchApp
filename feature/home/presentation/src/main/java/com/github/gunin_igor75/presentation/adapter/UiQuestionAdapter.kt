package com.github.gunin_igor75.presentation.adapter

import com.github.gunin_igor75.common.base.model.ListItem
import com.github.gunin_igor75.common.base.model.UiQuestion
import com.github.gunin_igor75.presentation.databinding.CardItemQuestionBinding
import com.github.gunin_igor75.presentation.utils.UiQuestionDffUtilCallback
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class UiQuestionAdapter(
    onClickQuestion: (String) -> Unit,
) : AsyncListDifferDelegationAdapter<ListItem>(UiQuestionDffUtilCallback) {

    init {
        delegatesManager
            .addDelegate(
                uiQuestionDelegate(
                    onClickQuestion = onClickQuestion
                )
            )
    }

    private fun uiQuestionDelegate(
        onClickQuestion: (String) -> Unit,
    ) =
        adapterDelegateViewBinding<UiQuestion, ListItem, CardItemQuestionBinding>(
            { layoutInflater, container ->
                CardItemQuestionBinding.inflate(
                    layoutInflater,
                    container,
                    false
                )
            }
        ) {
            bind {
                with(binding) {
                    buttonQuestion.text = item.text
                    root.setOnClickListener {
                        onClickQuestion(item.text)
                    }
                }
            }
        }
}