package com.example.superheroapi.domain.useCase

import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.data.utils.StateMessage
import com.example.superheroapi.domain.repository.MovieRepository
import com.example.superheroapi.domain.model.SuperHeroItem
import com.example.superheroapi.domain.model.WorkItem
import javax.inject.Inject

class GetWorkUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(id: Int): DataState<WorkItem> {
        return repository.getWorkFromApi(id)

    }

}