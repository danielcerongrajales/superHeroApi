package com.example.superheroapi.domain.useCase

import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.model.AppearanceItem
import com.example.superheroapi.domain.model.PowerStatsItem
import com.example.superheroapi.domain.repository.MovieRepository
import javax.inject.Inject

class GetPowerStatsUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): DataState<PowerStatsItem> {
        return repository.getPowerStatsFromApi(id)

    }

}