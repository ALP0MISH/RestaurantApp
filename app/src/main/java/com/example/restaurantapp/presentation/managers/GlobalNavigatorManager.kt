package com.example.restaurantapp.presentation.managers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import javax.inject.Inject

interface GlobalNavigatorManager {
    fun destinationsFlow(): Flow<Pair<String, Boolean>>
    fun navigateTo(destination: String, isClearBackstack: Boolean = false)
}

class NavigatorManagerImpl @Inject constructor() : GlobalNavigatorManager {

    private val destinationsFlow = MutableStateFlow("" to false)

    override fun destinationsFlow(): Flow<Pair<String, Boolean>> {
        return destinationsFlow.asStateFlow().filterNotNull()
    }

    override fun navigateTo(destination: String, isClearBackstack: Boolean) {
        destinationsFlow.tryEmit(Pair(destination, isClearBackstack))
    }

}