package com.example.superheroapi.data.network.model

data class SuperHero(
    val appearance: Appearance,
    val biography: Biography,
    val connections: Connections,
    val id: Int,
    val images: Images,
    val name: String,
    val powerstats: PowerStats,
    val slug: String,
    val work: Work
)