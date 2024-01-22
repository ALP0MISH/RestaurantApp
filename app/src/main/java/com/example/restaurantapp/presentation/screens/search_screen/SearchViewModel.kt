package com.example.restaurantapp.presentation.screens.search_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.use_cases.interactor.AllMenu
import com.example.restaurantapp.domain.use_cases.interactor.FetchMenuInteractor
import com.example.restaurantapp.presentation.mapper.toUi
import com.example.restaurantapp.presentation.models.MenuUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AllMenuUI(
    val allDrinks: List<MenuUi> = emptyList(),
    val allDesserts: List<MenuUi> = emptyList(),
    val allFastFoot: List<MenuUi> = emptyList(),
    val allHotDishes: List<MenuUi> = emptyList(),
    val allSalads: List<MenuUi> = emptyList(),
    var allItems: List<MenuUi> = emptyList()
) {
    init {
        val allLists = listOf(
            allDrinks,
            allDesserts,
            allFastFoot,
            allHotDishes,
            allSalads,
        )
        allItems = allLists.flatten()
    }
}

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val menuInteractor: FetchMenuInteractor,
) : ViewModel() {

    private val searchQueryFlow = MutableStateFlow("")

    private val _uiStateFlow = MutableStateFlow(SearchUiState())
    val uiStateFlow: StateFlow<SearchUiState> = _uiStateFlow.asStateFlow()

    init {
        searchQueryFlow.onEach { query ->
                _uiStateFlow.tryEmit(
                    _uiStateFlow.value.copy(
                        query = query, isLoading = true
                    )
                )
            }.debounce(300).onEach {
                fetchMenu(it)
            }.launchIn(viewModelScope)
    }

    private fun fetchMenu(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = menuInteractor.fetchAll()
            val menu = result.data
            if (menu == null) {
                return@launch
            } else {
                val filteredMenu = filterMenuByQuery(menu, query)
                val loaded = AllMenuUI(
                    allDrinks = filteredMenu.allDrinks.sortedBy { it.title }.map { it.toUi() },
                    allDesserts = filteredMenu.allDesserts.sortedBy { it.title }.map { it.toUi() },
                    allHotDishes = filteredMenu.allHotDishes.sortedBy { it.title }.map { it.toUi() },
                    allFastFoot = filteredMenu.allFastFoot.sortedBy { it.title }.map { it.toUi() },
                    allSalads = filteredMenu.allSalads.sortedBy { it.title }.map { it.toUi() },
                )
                _uiStateFlow.tryEmit(
                    _uiStateFlow.value.copy(
                        menu = loaded, isLoading = false
                    )
                )
            }
        }
    }

    fun onValueChange(value: String) {
        searchQueryFlow.tryEmit(value)
    }

    private fun filterMenuByQuery(menu: AllMenu, query: String): AllMenu {
        return menu.copy(
            allDrinks = filterMenuListByQuery(menu.allDrinks, query),
            allDesserts = filterMenuListByQuery(menu.allDesserts, query),
            allHotDishes = filterMenuListByQuery(menu.allHotDishes, query),
            allFastFoot = filterMenuListByQuery(menu.allFastFoot, query),
            allSalads = filterMenuListByQuery(menu.allSalads, query),
        )
    }

    private fun filterMenuListByQuery(menuList: List<MenuDomain>, query: String): List<MenuDomain> {
        return menuList.filter { it.title.contains(query, ignoreCase = true) }
    }
}