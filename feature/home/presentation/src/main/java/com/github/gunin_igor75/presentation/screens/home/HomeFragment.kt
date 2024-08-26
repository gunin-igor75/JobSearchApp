package com.github.gunin_igor75.presentation.screens.home

import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.UiOfferAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentHomeBinding
import com.github.gunin_igor75.presentation.utils.extension.followToLink


class HomeFragment: BaseFragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding(R.id.homeFragment)

    private val uiOfferAdapter: UiOfferAdapter by lazy {
        UiOfferAdapter(
            followToLink = { followToLink(it) },
            raiseResume = {}
        )
    }

}