package com.example.superheroapi.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.model.*
import com.example.superheroapi.domain.useCase.*

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class DetailsViewModel @Inject constructor(
    @Named("superHeroId") private val id: SuperHeroItem,
    private val getPowerStatsByIdUseCase: GetPowerStatsUseCase,
    private val getAppearanceByIdUseCase: GetAppearanceUseCase,
    private val getBiographyByIdUseCase: GetBiographyUseCase,
    private val geWorkByIdUseCase: GetWorkUseCase,
) : ViewModel() {

    val powerStatsById = MutableLiveData<PowerStatsItem>()
    val appearanceById = MutableLiveData<AppearanceItem>()
    val biographyById = MutableLiveData<BiographyItem>()
    val workById = MutableLiveData<WorkItem>()


    init {
//    _stateTwo.value= _stateTwo.value.copy(loadingBar = true)

        viewModelScope.launch {
            val result1 = async {
                when (val a = getPowerStatsByIdUseCase.invoke(id.id)) {
                    is DataState.Data -> powerStatsById.postValue(a.data)
                    is DataState.Error -> TODO()
                }
            }

            val result2 = async {
                when (val a = getAppearanceByIdUseCase.invoke(id.id)) {
                    is DataState.Data -> appearanceById.postValue(a.data)
                    is DataState.Error -> TODO()
                }
            }
            val result3 = async {
                when (val a = getBiographyByIdUseCase.invoke(id.id)) {
                    is DataState.Data -> biographyById.postValue(a.data)
                    is DataState.Error -> TODO()
                }
            }
            val result4 = async {
                when (val a = geWorkByIdUseCase.invoke(id.id)) {
                    is DataState.Data -> workById.postValue(a.data)
                    is DataState.Error -> TODO()
                }

            }
            result1.await()
            result2.await()
            result3.await()
            result4.await()


        }

    }

}