package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.Biography
import com.example.superheroapi.data.network.model.Images
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImagesItem(
    val lg: String,
    val md: String,
    val sm: String,
    val xs: String
) : Parcelable

fun Images.toDomain() = ImagesItem(
    lg = lg,
    md = md,
    sm = sm,
    xs = xs
)
