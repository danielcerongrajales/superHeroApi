package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.Biography
import com.example.superheroapi.data.network.model.Connections
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConnectionsItem(
    val groupAffiliation: String,
    val relatives: String
) : Parcelable

fun Connections.toDomain() = ConnectionsItem(
    groupAffiliation = groupAffiliation,
    relatives = relatives
)
