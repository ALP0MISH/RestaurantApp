package com.example.restaurantapp.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.current_user.IsOnboardingPassedUseCase
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import com.example.restaurantapp.presentation.models.toUser
import com.example.restaurantapp.presentation.navigations.navGraph.AUTH_NAV_GRAPH_ROUTE
import com.example.restaurantapp.presentation.navigations.navGraph.MAIN_NAV_GRAPH_ROUTE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val SPLASH_DELAY_TIME = 3_000L

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigatorManager: GlobalNavigatorManager,
    private val fetchCurrentUserUseCase: FetchCurrentUserUseCase,
    private val onboardingPassedUseCase: IsOnboardingPassedUseCase

) : ViewModel() {

    init {
        val currentUser = fetchCurrentUserUseCase().toUser()
        val isOnboardingPassed = onboardingPassedUseCase()
        viewModelScope.launch {
            delay(SPLASH_DELAY_TIME)
            val destination = when {
                currentUser.isNotUnknown() -> MAIN_NAV_GRAPH_ROUTE
                isOnboardingPassed -> AUTH_NAV_GRAPH_ROUTE
                else -> AUTH_NAV_GRAPH_ROUTE
            }
            navigatorManager.navigateTo(destination, true)
        }
    }
}