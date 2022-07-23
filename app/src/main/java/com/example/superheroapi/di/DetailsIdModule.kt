package com.example.superheroapi.di

import androidx.lifecycle.SavedStateHandle
import com.example.superheroapi.domain.model.SuperHeroItem
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object DetailsIdModule {
    @Provides
    @Named("superHeroId")
    fun movieIdProvider(stateHandle: SavedStateHandle): SuperHeroItem =
        stateHandle.get<SuperHeroItem>("idSuperHero")
            ?: throw IllegalStateException("superhero id not found in the state handle")

}