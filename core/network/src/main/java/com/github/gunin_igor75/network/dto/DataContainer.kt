package com.github.gunin_igor75.network.dto

data class DataContainer(
    val offers: List<OfferDto>?,
    val vacancies: List<VacanciesDto>
) {
    data class ButtonDto(
        val text: String,
    )

    data class OfferDto(
        val id: String,
        val title: String,
        val link: String,
        val button: ButtonDto?,
    )

    data class AddressDto(
        val town: String,
        val street: String,
        val house: String,
    )

    data class ExperienceDto(
        val previewText: String,
        val text: String,
    )

    data class SalaryDto(
        val short: String?,
        val full: String
    )

    data class VacanciesDto(
        val id: String,
        val lookingNumber: Int?,
        val title: String,
        val address: AddressDto,
        val company: String,
        val experience: ExperienceDto,
        val publishedDate: String,
        val isFavorite: Boolean,
        val salary: SalaryDto,
        val schedules: List<String>,
        val appliedNumber:Int?,
        val description: String?,
        val responsibilities: String,
        val questions: List<String>
    )
}
