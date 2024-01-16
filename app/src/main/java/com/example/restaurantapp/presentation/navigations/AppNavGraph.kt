package com.example.restaurantapp.presentation.navigations

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.restaurantapp.presentation.navigations.navGraph.AUTH_NAV_GRAPH_ROUTE
import com.example.restaurantapp.presentation.navigations.navGraph.AuthNavGraphRoot
import com.example.restaurantapp.presentation.navigations.navGraph.MAIN_NAV_GRAPH_ROUTE
import com.example.restaurantapp.presentation.navigations.navGraph.MainNavGraphRoot
import com.example.restaurantapp.presentation.screens.splash.SplashDestination
import com.example.restaurantapp.presentation.screens.splash.SplashScreen
import com.example.restaurantapp.presentation.screens.splash.SplashViewModel

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = SplashDestination.route()
    ) {
        composable(SplashDestination.route()) {
            val viewModel: SplashViewModel = hiltViewModel()
            viewModel
            SplashScreen()
        }
        composable(AUTH_NAV_GRAPH_ROUTE) {
            AuthNavGraphRoot()
        }
        composable(MAIN_NAV_GRAPH_ROUTE) {
            MainNavGraphRoot()
        }
    }
}