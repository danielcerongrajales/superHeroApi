package com.example.superheroapi.domain.model

import android.os.Parcelable
import com.example.superheroapi.data.network.model.*
import kotlinx.parcelize.Parcelize

@Parcelize
data class SuperHeroItem(
    val id: Int,
    val name: String,
    val slug: String,
    val appearance: AppearanceItem,
    val biography: BiographyItem,
    val connections: ConnectionsItem,
    val images: ImagesItem,
    val powerstats: PowerStatsItem,
    val work: WorkItem
) : Parcelable

fun SuperHero.toDomain() = SuperHeroItem(
    id = id,
    name = name,
    slug = slug,
    images = images.toDomain(),
    appearance = appearance.toDomain(),
    biography = biography.toDomain(),
    connections = connections.toDomain(),
    powerstats = powerstats.toDomain(),
    work = work.toDomain()

)


