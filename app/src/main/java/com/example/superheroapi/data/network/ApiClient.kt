package com.example.superheroapi.data.network

import com.example.superheroapi.data.network.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("superhero-api/api/all.json")
    suspend fun getSuperHeros(): Response<SuperHeros>

    @GET("superhero-api/api/id/{id}.json")
    suspend fun getSuperHero(@Path("id") id:Int): Response<SuperHero>

    @GET("superhero-api/api/powerstats/{id}.json")
    suspend fun getPowerStats(@Path("id") id:Int): Response<PowerStats>

    @GET("superhero-api/api/appearance/{id}.json")
    suspend fun getAppearance(@Path("id") id:Int): Response<Appearance>

    @GET("superhero-api/api/biography/{id}.json")
    suspend fun getBiography(@Path("id") id:Int): Response<Biography>

    @GET("superhero-api/api/work/{id}.json")
    suspend fun getWork(@Path("id") id:Int): Response<Work>



}