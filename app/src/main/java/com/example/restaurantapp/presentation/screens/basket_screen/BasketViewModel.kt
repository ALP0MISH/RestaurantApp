package com.example.restaurantapp.presentation.screens.basket_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.use_cases.basket.AddToBasketUseCase
import com.example.restaurantapp.domain.use_cases.basket.FetchAllFromBasketUseCase
import com.example.restaurantapp.presentation.mapper.toMenuUi
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val addToBasketUseCase: AddToBasketUseCase,
    private val fetchAllFromBasketUseCase: FetchAllFromBasketUseCase
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(BasketUIState())
    val uiStateFlow: StateFlow<BasketUIState> = _uiStateFlow.asStateFlow()

    init {
        fetchFromBasket()
    }

    fun addToBasket(menuUi: MenuUi) {
        Log.i("Abdurahman", "menuUIViewModel = $menuUi")
        viewModelScope.launch(Dispatchers.IO) {
            addToBasketUseCase.addToBasket(menuUi.toMenuUi())
        }
    }

    private fun fetchFromBasket() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = fetchAllFromBasketUseCase.fetchFromBasket()) {

                is Result.Success -> {
                    val data = result.data?.map { it.toUi() } ?: emptyList()
                    Log.i("Abdurahman", "$data")
                    val updatedUiState = BasketUIState(fetchFromBasket = data)
                    _uiStateFlow.emit(updatedUiState)
                }

                is Result.Error -> {
                    val errorMessage = result.message
                    val updatedUiState =
                        BasketUIState().copy(error = errorMessage ?: DEFAULT_ERROR_MESSAGE)
                    _uiStateFlow.emit(updatedUiState)
                }
            }
        }
    }
}