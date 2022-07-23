package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.PowerStats
import kotlinx.parcelize.Parcelize

@Parcelize
data class PowerStatsItem(
    val combat: Int,
    val durability: Int,
    val intelligence: Int,
    val power: Int,
    val speed: Int,
    val strength: Int
) : Parcelable

fun PowerStats.toDomain() = PowerStatsItem(
    combat = combat,
    durability = durability,
    intelligence = intelligence,
    power = power,
    speed = speed,
    strength = strength
)
