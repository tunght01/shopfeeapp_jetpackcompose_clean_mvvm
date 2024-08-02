package com.example.shopfeeap_mvvm.presentation.home_creen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopfeeap_mvvm.common.Resource
import com.example.shopfeeap_mvvm.domain.use_case.GetDrinkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinksViewModel @Inject constructor(
    private val getDrinksUseCase: GetDrinkUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DrinksState())
    val state: State<DrinksState> = _state

    init {
        getDrinks()
    }

    private fun getDrinks() {
        viewModelScope.launch {
            val result = getDrinksUseCase()
            when (result) {
                is Resource.Success<*> -> {
                    _state.value = DrinksState(drinks = result.data ?: emptyList())
                }
                is Resource.Error<*> -> {
                    _state.value = DrinksState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading<*> -> {
                    _state.value = DrinksState(isLoading = true)
                }
            }
        }
    }
}
