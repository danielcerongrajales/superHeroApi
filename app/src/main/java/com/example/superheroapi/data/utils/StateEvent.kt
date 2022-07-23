package com.example.superheroapi.data.utils

import com.example.superheroapi.ui.viewmodel.SuperHeroItemUiState

interface StateEvent {

    fun errorInfo(): String
    fun errorInfos(): SuperHeroItemUiState
    fun eventName(): String
    fun shouldDisplayProgressBar(): Boolean
}
