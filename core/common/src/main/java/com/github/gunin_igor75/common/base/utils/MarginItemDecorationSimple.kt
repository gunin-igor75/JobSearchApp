package com.github.gunin_igor75.common.base.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecorationSimple(
    private val spaceSize: Int,
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        with(outRect) {
            top = spaceSize
        }
    }
}