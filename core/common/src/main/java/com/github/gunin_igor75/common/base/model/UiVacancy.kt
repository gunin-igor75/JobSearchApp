package com.github.gunin_igor75.common.base.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UiVacancy(
    override val listItemId: String,
    val title: String,
    val salary: String,
    val experiens: String,
    val schedules: String,
    val appliedNumber: Int? = null,
    val lookingNumber: Int? = null,
    val address: String,
    val description: String?,
    val responsibilities: String,
    val questions: List<String>,
    val isFavorite: Boolean,
    val town: String,
    val company: String,
    val publishedDate: String,
): ListItem, Parcelable
