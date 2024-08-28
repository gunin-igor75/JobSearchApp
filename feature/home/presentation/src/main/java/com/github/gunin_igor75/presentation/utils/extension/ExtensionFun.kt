package com.github.gunin_igor75.presentation.utils.extension

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment


fun Fragment.followToLink(link: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link)).apply {
        addCategory(Intent.CATEGORY_BROWSABLE)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    startActivity(intent)
}


fun Fragment.dpToPx(dpValue: Int): Int {
    val dpRatio = resources.displayMetrics.density
    return (dpValue * dpRatio).toInt()
}