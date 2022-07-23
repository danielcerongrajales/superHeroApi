package com.example.superheroapi.ui.viewmodel

import androidx.lifecycle.*
import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.domain.useCase.GetSuperHerosUseCase
import com.example.superheroapi.data.utils.StateMessage
import com.example.superheroapi.domain.model.SuperHeroItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuperHeroViewModel @Inject constructor(val getSuperHerosUseCase: GetSuperHerosUseCase) :
    ViewModel() {

    private val _stateTwo = MutableStateFlow(UiStateTwo())
    val stateTwo: StateFlow<UiStateTwo> = _stateTwo.asStateFlow()

    data class UiStateTwo(
        val loadingBar: Boolean = false,
        val superHeros: DataState<List<SuperHeroItemUiState>>? = null,
        val stateMessage: StateMessage? = StateMessage()
    )


    init {

        viewModelScope.launch {
            _stateTwo.updateAndGet {
                _stateTwo.value.copy(loadingBar = true)
            }

            _stateTwo.value = _stateTwo.value.copy(
                loadingBar = false, superHeros =
                when (val a = getSuperHerosUseCase()) {
                    is DataState.Data -> {
                        DataState.Data(a.response, a.data?.map { it.toUiState() })
                    }
                    is DataState.Error -> {
                        DataState.Data(a.response, a.data?.map { it.toUiState() })
                    }
                }

            )

        }


    }


    private fun SuperHeroItem.toUiState() = SuperHeroItemUiState(
        this,
        onBookmark = {
            viewModelScope.launch {
            }
        }

    )


}


data class SuperHeroItemUiState(
    val superHeroItem: SuperHeroItem,
    val onBookmark: () -> Unit
)

