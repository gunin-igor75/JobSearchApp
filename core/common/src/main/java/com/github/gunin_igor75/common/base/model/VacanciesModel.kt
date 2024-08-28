package com.github.gunin_igor75.common.base.model

data class VacanciesModel(
    val id: String,
    val lookingNumber: Int?,
    val title: String,
    val addressModel: AddressModel,
    val company: String,
    val experience: ExperienceModel,
    val publishedDate: String,
    val isFavorite: Boolean,
    val salary: SalaryModel,
    val schedules: List<String>,
    val appliedNumber:Int?,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>
)
