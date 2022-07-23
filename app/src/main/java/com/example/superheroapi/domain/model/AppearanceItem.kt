package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.Appearance
import kotlinx.parcelize.Parcelize

@Parcelize
data class AppearanceItem(
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: List<String>,
    val race: String? = "empty",
    val weight: List<String>
) : Parcelable

fun Appearance.toDomain() = AppearanceItem(
    eyeColor = eyeColor,
    gender = gender,
    hairColor = hairColor,
    height = height,
    race = race,
    weight = weight
)
