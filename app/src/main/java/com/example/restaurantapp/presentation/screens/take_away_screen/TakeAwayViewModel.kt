package com.example.restaurantapp.presentation.screens.take_away_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain
import com.example.restaurantapp.domain.use_cases.current_user.FetchAllUserUseCase
import com.example.restaurantapp.domain.use_cases.interactor.AllMenu
import com.example.restaurantapp.domain.use_cases.interactor.FetchMenuInteractor
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.restaurantapp.presentation.models.User
import com.example.restaurantapp.presentation.models.toUser
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeAwayViewModel @Inject constructor(
    private val menuInteractor: FetchMenuInteractor,
    private val showToastUseCase: ShowToastUseCase,
    private val currentUserUseCase: FetchAllUserUseCase
) : ViewModel() {

    private var isMenuFetched = false

    private val _uiStateFlow = MutableStateFlow<TakeAwayUiState>(TakeAwayUiState.Loading)
    val uiState: StateFlow<TakeAwayUiState> = _uiStateFlow.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(TakeAwayUiState.Error(throwable.localizedMessage ?: ""))
    }

    init {
        if (!isMenuFetched) {
            fetchMenu()
        }
    }

    private fun fetchMenu() {
        viewModelScope.launch(handle + Dispatchers.IO) {
            _uiStateFlow.tryEmit(TakeAwayUiState.Loading)
            val result = menuInteractor.fetchAll()
            var loaded = TakeAwayUiState.Loaded(user = currentUserUseCase().toUser())
            when (result) {
                is Result.Error -> {
                    showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                    Log.e("Restaurant", "message = ${result.message}")
                }

                is Result.Success -> {
                    val menu = result.data
                    if (menu == null) {
                        showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                        Log.e("Restaurant", "data = ${result.message}")
                    } else {
                        loaded = TakeAwayUiState.Loaded(
                            drinks = result.data?.allDrinks?.map { it.toUi() } ?: emptyList(),
                            desserts = result.data?.allDesserts?.map { it.toUi() } ?: emptyList(),
                            hotDishes = result.data?.allHotDishes?.map { it.toUi() } ?: emptyList(),
                            fastFoot = result.data?.allFastFoot?.map { it.toUi() } ?: emptyList(),
                            salads = result.data?.allSalads?.map { it.toUi() } ?: emptyList(),
                            user = currentUserUseCase().toUser(),
                        )
                    }
                }
            }
            _uiStateFlow.tryEmit(loaded)
            isMenuFetched = true
        }
    }
}