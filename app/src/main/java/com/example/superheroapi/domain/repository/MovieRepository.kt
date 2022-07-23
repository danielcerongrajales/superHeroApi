package com.example.superheroapi.domain.repository


import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRepository {

    suspend fun getSuperHerosFromApi(): DataState<List<SuperHeroItem>>
    suspend fun getSuperHeroFromApi(@Path("id") id: Int): DataState<SuperHeroItem>
    suspend fun getPowerStatsFromApi(@Path("id") id: Int): DataState<PowerStatsItem>
    suspend fun getAppearanceFromApi(@Path("id") id: Int): DataState<AppearanceItem>
    suspend fun getBiographyFromApi(@Path("id") id: Int): DataState<BiographyItem>
    suspend fun getWorkFromApi(@Path("id") id: Int): DataState<WorkItem>

}