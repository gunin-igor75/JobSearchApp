package com.github.gunin_igor75.presentation.screens.vacanciesdetails

import android.os.Bundle
import android.view.View
import androidx.core.view.get
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.gunin_igor75.common.base.base.BaseFragment
import com.github.gunin_igor75.common.base.utils.Constants.Companion.INDEX_MENU_ITEM_FAVORITE
import com.github.gunin_igor75.presentation.R
import com.github.gunin_igor75.presentation.adapter.UiQuestionAdapter
import com.github.gunin_igor75.presentation.databinding.FragmentVacanciesDetailsBinding
import com.github.gunin_igor75.presentation.model.UiContainer
import com.github.gunin_igor75.presentation.model.UiQuestion
import com.github.gunin_igor75.presentation.model.UiVacancy
import com.github.gunin_igor75.presentation.utils.decoration.MarginItemDecorationSimple
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class VacanciesDetailsFragment : BaseFragment(R.layout.fragment_vacancies_details) {
    private val binding: FragmentVacanciesDetailsBinding by viewBinding(R.id.vacanciesDetailsFragment)

    private val args by navArgs<VacanciesDetailsFragmentArgs>()

    private val vm: VacanciesDetailsViewModel by viewModel()

    private val uiQuestionAdapter: UiQuestionAdapter by lazy {
        UiQuestionAdapter {
            val action =
                VacanciesDetailsFragmentDirections.actionVacanciesDetailsFragment2ToResponseFragment(
                    UiContainer(
                        vacancyName = args.vacancy.title,
                        question = it
                    )
                )
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUiElement()
        observeIsFavorite()
        setupRecyclerView()
        onClickButtonSendResponse()
        clickBack()
    }

    private fun setupRecyclerView() {
        val margin = resources.getDimensionPixelSize(com.github.gunin_igor75.common.R.dimen.dp_1x)
        val marginDecoration = MarginItemDecorationSimple(margin)
        val questions: List<UiQuestion> = vm.getUiQuestions(args.vacancy.questions)
        with(binding.recyclerView) {
            adapter = uiQuestionAdapter
            addItemDecoration(marginDecoration)
        }
        uiQuestionAdapter.items = questions
    }

    private fun setupUiElement() {
        val vacancy = args.vacancy
        with(binding) {
            textViewTitle.text = vacancy.title
            textViewSalaryFull.text = vacancy.salary
            val experience = String.format(getString(R.string.experience_job), vacancy.experiens)
            textViewExperience.text = experience
            textViewSchedules.text = vacancy.schedules
            setupBlocksAppliedLooking(vacancy)
            includeLocation.textViewVacanciesCompany.text = vacancy.company
            includeLocation.textViewAddressLocation.text = vacancy.address
            setupDescriptionCompany(vacancy)
            textViewTask.text = vacancy.responsibilities

        }
    }

    private fun setupBlocksAppliedLooking(vacancy: UiVacancy) {
        with(binding) {
            if (vacancy.appliedNumber == null && vacancy.lookingNumber == null) {
                frameLayout.visibility = View.GONE
                return
            }
            frameLayout.visibility = View.VISIBLE
            if (vacancy.appliedNumber != null) {
                val num = vacancy.appliedNumber
                val appliedText = resources.getQuantityString(R.plurals.people_response, num, num)
                includeAppliedLooking.textViewTitleApplied.text = appliedText
                includeAppliedLooking.linearLayoutApplied.visibility = View.VISIBLE
            } else {
                includeAppliedLooking.linearLayoutApplied.visibility = View.GONE
            }
            if (vacancy.lookingNumber != null) {
                val num = vacancy.lookingNumber
                val lookingText = resources.getQuantityString(R.plurals.people_looking, num, num)
                includeAppliedLooking.textViewTitleLooking.text = lookingText
                includeAppliedLooking.linearLayoutLooking.visibility = View.VISIBLE
            } else {
                includeAppliedLooking.linearLayoutLooking.visibility = View.GONE
            }
        }
    }

    private fun setupDescriptionCompany(vacancy: UiVacancy) {
        with(binding) {
            if (vacancy.description == null) {
                textViewDescriptionCompany.visibility = View.GONE
                return
            }
            textViewDescriptionCompany.visibility = View.VISIBLE
            textViewDescriptionCompany.text = vacancy.description
        }
    }

    private fun observeIsFavorite() {
        val vacancy = args.vacancy
        val id = vacancy.listItemId
        vm.isFavorite(id)
        lifecycleScope.launch {
            vm.isFavoriteState.flowWithLifecycle(viewLifecycleOwner.lifecycle)
                .collect { isFavorite ->
                    val icon =
                        if (isFavorite) com.github.gunin_igor75.common.R.drawable.ic_favorite_selected
                        else com.github.gunin_igor75.common.R.drawable.ic_favorites
                    val image = binding.toolBar.menu[INDEX_MENU_ITEM_FAVORITE]
                    image.setIcon(icon)
                    image.setOnMenuItemClickListener {
                        if (isFavorite) vm.removeFromFavorites(id) else vm.addFavoriteVacancies(
                            vacancy
                        )
                        true
                    }
                }
        }
    }

    private fun clickBack() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onClickButtonSendResponse() {
        binding.buttonRespond.setOnClickListener {
            val uiContainer = UiContainer(args.vacancy.title)
            val action =
                VacanciesDetailsFragmentDirections.actionVacanciesDetailsFragment2ToResponseFragment(
                    uiContainer
                )
            findNavController().navigate(action)
        }
    }
}
