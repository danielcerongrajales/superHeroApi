package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.Work
import kotlinx.parcelize.Parcelize

@Parcelize
data class WorkItem(
    val base: String,
    val occupation: String
) : Parcelable

fun Work.toDomain() = WorkItem(
    base = base,
    occupation = occupation
)
