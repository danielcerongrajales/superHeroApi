package com.example.superheroapi.data.network

import javax.inject.Inject

class SuperHeroService @Inject constructor(private val retrofit: ApiClient){

    suspend fun getSuperHeros()= retrofit.getSuperHeros()

    suspend fun getSuperHero( id:Int)= retrofit.getSuperHero(id)

    suspend fun getPowerStats( id:Int)= retrofit.getPowerStats(id)

    suspend fun getAppearance( id:Int)= retrofit.getAppearance(id)

    suspend fun getBiography( id:Int)= retrofit.getBiography(id)

    suspend fun getWork( id:Int)= retrofit.getWork(id)



}