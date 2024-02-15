package com.example.restaurantapp.presentation.screens.basket_screen

import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.use_cases.basket.AddToBasketUseCase
import com.example.restaurantapp.domain.use_cases.basket.FetchAllFromBasketUseCase
import com.example.restaurantapp.domain.use_cases.basket.FetchBasketByIdUseCase
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.mapper.toMenuUi
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.restaurantapp.presentation.models.MenuUi
import com.example.restaurantapp.presentation.screens.edit_profile.SUCCESS_MESSAGE
import com.example.restaurantapp.presentation.screens.take_away_screen.INTERNET_ERROR_MESSAGE
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayUiState
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val SUCCESS_ADDED_TO_BASKET = "Successfully Added to Basket"

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val addToBasketUseCase: AddToBasketUseCase,
    private val showToastUseCase: ShowToastUseCase,
    private val fetchAllFromBasketUseCase: FetchAllFromBasketUseCase,
    private val connectivityManager: ConnectivityManager,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(BasketUIState())
    val uiStateFlow: StateFlow<BasketUIState> = _uiStateFlow.asStateFlow()

    init {
        fetchFromBasket()
    }

    fun addToBasket(menuUi: MenuUi) {
        if (!isNetworkAvailable()) {
            _uiStateFlow.tryEmit(
                BasketUIState(
                    error = INTERNET_ERROR_MESSAGE
                )
            )
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                addToBasketUseCase.addToBasket(menuUi.toMenuUi())
                withContext(Dispatchers.Main) {
                    showToastUseCase.showToast(SUCCESS_ADDED_TO_BASKET)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToastUseCase.showToast(DEFAULT_ERROR_MESSAGE)
                }
            }
        }
    }


    fun fetchFromBasket() {
        viewModelScope.launch(Dispatchers.IO) {

            when (val result = fetchAllFromBasketUseCase.fetchFromBasket()) {
                is Result.Success -> {
                    val data = result.data?.map { it.toUi() } ?: emptyList()
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

    private fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}