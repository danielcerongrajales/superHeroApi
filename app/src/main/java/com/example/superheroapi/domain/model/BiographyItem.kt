package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.Biography
import kotlinx.parcelize.Parcelize

@Parcelize
data class BiographyItem(
    val aliases: List<String>,
    val alignment: String,
    val alterEgos: String,
    val firstAppearance: String,
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String? = "empty"
) : Parcelable

fun Biography.toDomain() = BiographyItem(
    aliases = aliases,
    alignment = alignment,
    alterEgos = alterEgos,
    firstAppearance = firstAppearance,
    fullName = fullName,
    placeOfBirth = placeOfBirth,
    publisher = publisher
)
