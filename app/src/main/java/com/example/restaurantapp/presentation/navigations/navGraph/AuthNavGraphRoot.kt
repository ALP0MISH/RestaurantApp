package com.example.restaurantapp.presentation.navigations.navGraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.presentation.auth.login.LoginDestination
import com.example.restaurantapp.presentation.auth.login.LoginScreen
import com.example.restaurantapp.presentation.auth.login.LoginViewModel
import com.example.restaurantapp.presentation.auth.signup.SignUpDestination
import com.example.restaurantapp.presentation.auth.signup.SignUpScreen
import com.example.restaurantapp.presentation.auth.signup.SignUpViewModel
import com.example.restaurantapp.presentation.screens.onboarding.OnBoardingDestination
import com.example.restaurantapp.presentation.screens.onboarding.OnBoardingScreen
import com.example.restaurantapp.presentation.screens.onboarding.OnBoardingViewModel
import com.example.restaurantapp.presentation.screens.select_category.SelectCategory
import com.example.restaurantapp.presentation.screens.select_category.SelectCategoryDestination
import com.example.restaurantapp.presentation.screens.select_category.SelectCategoryViewModel

const val AUTH_NAV_GRAPH_ROUTE = "auth_nav_graph_route"

@Composable
fun AuthNavGraphRoot() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = OnBoardingDestination.route()
    ) {
        composable(OnBoardingDestination.route()) {
            val viewModel: OnBoardingViewModel = hiltViewModel()
            val navcontroller by viewModel.navControllerFlow.collectAsStateWithLifecycle(
                initialValue = null
            )
            LaunchedEffect(key1 = navcontroller) {
                if (navcontroller != null) navController.navigate(navcontroller!!)
            }
            OnBoardingScreen(
                navigateToLoginScreen = { viewModel.onboardingFinished() }
            )
        }
        composable(SelectCategoryDestination.route()) {
            val viewModel: SelectCategoryViewModel = hiltViewModel()
            val navcontroller by viewModel.navControllerFlow.collectAsStateWithLifecycle(
                initialValue = null
            )
            LaunchedEffect(key1 = navcontroller) {
                if (navcontroller != null) navController.navigate(navcontroller!!)
            }
            SelectCategory(
                isTakeAwayScreen = { viewModel.onTakeAwayClick() },
                isTableScreen = { viewModel.onTakeAwayClick() }
            )
        }
        composable(LoginDestination.route()) {
            val viewModel: LoginViewModel = hiltViewModel()
            val navcontroller by viewModel.navControllerFlow.collectAsStateWithLifecycle(
                initialValue = null
            )
            LaunchedEffect(key1 = navcontroller) {
                if (navcontroller != null) navController.navigate(navcontroller!!)
            }
            LoginScreen(
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent,
            )
        }
        composable(SignUpDestination.route()) {
            val viewModel: SignUpViewModel = hiltViewModel()
            val navcontroller by viewModel.navControllerFlow.collectAsStateWithLifecycle(
                initialValue = null,
            )
            LaunchedEffect(key1 = navcontroller) {
                if (navcontroller != null) navController.navigate(navcontroller!!)
            }
            SignUpScreen(
                uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
                onEvent = viewModel::onEvent
            )
        }
    }
}