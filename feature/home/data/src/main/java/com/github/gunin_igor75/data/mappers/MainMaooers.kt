package com.github.gunin_igor75.data.mappers

import com.github.gunin_igor75.domain.model.AddressModel
import com.github.gunin_igor75.domain.model.ExperienceModel
import com.github.gunin_igor75.domain.model.OfferModel
import com.github.gunin_igor75.domain.model.SalaryModel
import com.github.gunin_igor75.domain.model.VacanciesModel
import com.github.gunin_igor75.network.dto.DataContainer
import java.util.UUID


fun List<DataContainer.OfferDto>.toOffersModels() = map { it.toOfferModel() }

fun List<DataContainer.VacanciesDto>.toVacanciesModels() = map { it.toVacanciesModel() }

private fun DataContainer.OfferDto.toOfferModel() = OfferModel(
    listItemId = UUID.randomUUID().toString(),
    id = id,
    title = title,
    link = link,
    buttonText = button?.text
)

private fun DataContainer.AddressDto.toAddressModel() = AddressModel(town, street, house)

private fun DataContainer.ExperienceDto.toExperienceModel() = ExperienceModel(previewText, text)

private fun DataContainer.SalaryDto.toSalaryModel() = SalaryModel(short, full)


private fun DataContainer.VacanciesDto.toVacanciesModel() = VacanciesModel(
    id = id,
    lookingNumber = lookingNumber,
    title = title,
    addressModel = address.toAddressModel(),
    company = company,
    experience = experience.toExperienceModel(),
    publishedDate = publishedDate,
    isFavorite = isFavorite,
    salary = salary.toSalaryModel(),
    schedules = schedules,
    appliedNumber = appliedNumber,
    description = description,
    responsibilities = responsibilities,
    questions = questions
)
