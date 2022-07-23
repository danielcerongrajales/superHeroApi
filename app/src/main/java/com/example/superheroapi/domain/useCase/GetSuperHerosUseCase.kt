package com.example.superheroapi.domain.useCase

import android.util.Log
import androidx.paging.Pager
import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.repository.MovieRepository
import com.example.superheroapi.domain.model.SuperHeroItem
import javax.inject.Inject

class GetSuperHerosUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(): DataState<List<SuperHeroItem>> {
        val ala = repository.getSuperHerosFromApi()
        return ala
    }


}