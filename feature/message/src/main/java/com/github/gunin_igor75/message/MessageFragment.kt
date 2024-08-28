package com.github.gunin_igor75.message

import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.message.databinding.FragmentMessageBinding

class MessageFragment : BaseFragment(R.layout.fragment_message) {
    private val binding: FragmentMessageBinding by viewBinding(R.id.message)
}