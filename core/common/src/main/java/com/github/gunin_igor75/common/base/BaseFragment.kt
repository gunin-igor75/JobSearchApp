package com.github.gunin_igor75.common.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0): Fragment(contentLayoutId)