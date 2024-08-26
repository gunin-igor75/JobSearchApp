package com.github.gunin_igor75.presentation.utils.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorationOffers(
    private val spaceSize: Int,
): RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect){
            val count =  parent.adapter?.itemCount?: 0
            if (parent.getChildAdapterPosition(view) != count - 1) {
                right = spaceSize
            }
        }
    }
}