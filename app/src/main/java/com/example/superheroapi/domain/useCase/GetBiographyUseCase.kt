package com.example.superheroapi.domain.useCase

import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.model.BiographyItem
import com.example.superheroapi.domain.repository.MovieRepository
import javax.inject.Inject

class GetBiographyUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(id: Int): DataState<BiographyItem> {
        return repository.getBiographyFromApi(id)

    }


}