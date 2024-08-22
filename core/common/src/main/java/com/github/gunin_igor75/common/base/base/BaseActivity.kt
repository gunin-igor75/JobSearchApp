package com.github.gunin_igor75.common.base.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity(@LayoutRes contentLayoutId:Int = 0): AppCompatActivity(contentLayoutId)