package com.example.restaurantapp.presentation.screens.take_away_screen

import android.net.ConnectivityManager
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.interactor.FetchMenuInteractor
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.restaurantapp.presentation.models.toUser
import com.example.socialapp.data.repositories.DEFAULT_ERROR_MESSAGE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

const val INTERNET_ERROR_MESSAGE = "No internet connection"

@HiltViewModel
class TakeAwayViewModel @Inject constructor(
    private val menuInteractor: FetchMenuInteractor,
    private val showToastUseCase: ShowToastUseCase,
    private val fetchAllUserUseCase: FetchCurrentUserUseCase,
    private val connectivityManager: ConnectivityManager,
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow<TakeAwayUiState>(TakeAwayUiState.Loading)
    val uiState: StateFlow<TakeAwayUiState> = _uiStateFlow.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(TakeAwayUiState.Error(throwable.localizedMessage ?: ""))
    }

    init {
        fetchMenu()
    }

    fun fetchMenu() {
        if (!isNetworkAvailable()) {
            _uiStateFlow.tryEmit(
                TakeAwayUiState.Error(
                    message = INTERNET_ERROR_MESSAGE
                )
            )
            return
        }

        viewModelScope.launch(handle + Dispatchers.IO) {
            _uiStateFlow.tryEmit(TakeAwayUiState.Loading)
            when (val result = menuInteractor.fetchAll()) {
                is Result.Error -> {
                    result.message?.let { message ->
                        _uiStateFlow.tryEmit(
                            TakeAwayUiState.Error(
                                message = message
                            )
                        )
                    }
                    showToastUseCase.showToast(result.message ?: DEFAULT_ERROR_MESSAGE)
                }

                is Result.Success -> {
                    result.data.let { menu ->
                        _uiStateFlow.tryEmit(
                            TakeAwayUiState.Loaded(
                                drinks = menu?.allDrinks?.map { it.toUi() } ?: emptyList(),
                                desserts = menu?.allDesserts?.map { it.toUi() } ?: emptyList(),
                                hotDishes = menu?.allHotDishes?.map { it.toUi() } ?: emptyList(),
                                fastFoot = menu?.allFastFoot?.map { it.toUi() } ?: emptyList(),
                                salads = menu?.allSalads?.map { it.toUi() } ?: emptyList(),
                                user = fetchAllUserUseCase().toUser(),
                            )
                        )
                    }
                }
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
