package com.example.superheroapi.di

import com.example.superheroapi.data.SuperHeroRepositoryImpl
import com.example.superheroapi.data.network.SuperHeroService
import com.example.superheroapi.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieRepository(
        api: SuperHeroService,
    ): MovieRepository {
        return SuperHeroRepositoryImpl(api)
    }
}