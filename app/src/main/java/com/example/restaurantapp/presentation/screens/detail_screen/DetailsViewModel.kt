package com.example.restaurantapp.presentation.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.use_cases.menu.FetchCategoriesUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchDessertsByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchDrinksByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchFastFoodByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchHotDishesByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchSaladByIdUseCase
import com.example.restaurantapp.presentation.mapper.toUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


enum class ItemDetailType {
    HOT_DISHES,
    DESSERTS,
    DRINKS,
    SALADS,
    FAST_FOOD,
    UNKNOWN,
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val fetchDessertsByIdUseCase: FetchDessertsByIdUseCase,
    private val fetchDrinksById: FetchDrinksByIdUseCase,
    private val fetchFastFoodByIdUseCase: FetchFastFoodByIdUseCase,
    private val fetchHotDishesByIdUseCase: FetchHotDishesByIdUseCase,
    private val fetchSaladByIdUseCase: FetchSaladByIdUseCase,
    private val fetchCategories: FetchCategoriesUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiState: StateFlow<DetailsUiState> = _uiState.asStateFlow()

    private val handle = CoroutineExceptionHandler { _, throwable ->
        _uiState.tryEmit(DetailsUiState.Error(throwable.localizedMessage ?: ""))
    }

    fun findFoodByIdAndFetch(
        foodId: String, categoryId: String
    ) {
        viewModelScope.launch(handle + Dispatchers.IO) {
            val categoryResponse = fetchCategories(
                categoryId
            )
            categoryResponse.data?.map { it ->
                when (it.name) {
                    ItemDetailType.DESSERTS.name -> {
                        val response = fetchDessertsByIdUseCase(foodId)
                        response.data?.let {
                            _uiState.tryEmit(DetailsUiState.Loaded(it.toUi()))
                        }
                    }

                    ItemDetailType.HOT_DISHES.name -> {
                        val response = fetchHotDishesByIdUseCase(foodId)
                        response.data?.let {
                            _uiState.tryEmit(DetailsUiState.Loaded(it.toUi()))
                        }
                    }

                    ItemDetailType.FAST_FOOD.name -> {
                        val response = fetchFastFoodByIdUseCase(foodId)
                        response.data?.let {
                            _uiState.tryEmit(DetailsUiState.Loaded(it.toUi()))
                        }
                    }

                    ItemDetailType.SALADS.name -> {
                        val response = fetchSaladByIdUseCase(foodId)
                        response.data?.let {
                            _uiState.tryEmit(DetailsUiState.Loaded(it.toUi()))
                        }
                    }

                    ItemDetailType.DRINKS.name -> {
                        val response = fetchDrinksById(foodId)
                        response.data?.let {
                            _uiState.tryEmit(DetailsUiState.Loaded(it.toUi()))
                        }
                    }
                    else -> {
                        ItemDetailType.UNKNOWN
                    }
                }
            }
        }
    }
}