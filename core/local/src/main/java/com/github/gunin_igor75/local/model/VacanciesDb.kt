package com.github.gunin_igor75.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class VacanciesDb(
    @PrimaryKey
    val id: String,
    val lookingNumber: Int? = null,
    val isFavorite: Boolean,
    val title: String,
    val company: String,
    val previewText: String,
    val publishedDate: String,
)
