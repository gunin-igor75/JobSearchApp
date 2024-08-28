package com.github.gunin_igor75.presentation.screens.vacancies

import android.os.Bundle
import android.view.View
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.UiVacancyAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentVacanciesBinding
import com.github.gunin_igor75.presentation.utils.decoration.MarginItemDecorationSimple
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class VacanciesFragment : BaseFragment(R.layout.fragment_vacancies) {
    private val binding: FragmentVacanciesBinding by viewBinding(R.id.vacanciesFragment)

    private val vm: VacanciesViewModel by viewModel()

    private val uiVacancyAdapter: UiVacancyAdapter by lazy {
        UiVacancyAdapter(
            onClickItem = {
                val action =
                    VacanciesFragmentDirections.actionVacanciesFragment2ToVacanciesDetailsFragment2(it)
                findNavController().navigate(action)
            },
            onClickFavorite = {
                if (it.isFavorite) vm.removeFromFavorites(it.listItemId) else vm.addFavoriteVacancies(it)
            }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCountVacancies()
        observeVacancies()
        clickBack()
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(com.github.gunin_igor75.common.R.dimen.dp_1x)
        val marginDecoration = MarginItemDecorationSimple(margin)
        with(binding.recyclerView) {
            adapter = uiVacancyAdapter
            addItemDecoration(marginDecoration)
        }
    }

    private fun observeCountVacancies() {
        lifecycleScope.launch {
            vm.getCountVacancies().flowWithLifecycle(viewLifecycleOwner.lifecycle).collect{ num ->
                val text = resources.getQuantityString(R.plurals.count_vacancies, num, num)
                binding.textViewCountVacancies.text = text
            }
        }
    }

    private fun observeVacancies() {
        lifecycleScope.launch {
            vm.getVacancies().flowWithLifecycle(viewLifecycleOwner.lifecycle).collect{
                uiVacancyAdapter.items = it
            }
        }
    }


    private fun clickBack() {
        binding.includeFindJobVacancies.textInputLayout.setStartIconOnClickListener {
            findNavController().popBackStack()
        }
    }
}