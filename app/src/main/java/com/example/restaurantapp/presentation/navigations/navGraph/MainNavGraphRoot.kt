package com.example.restaurantapp.presentation.navigations.navGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.presentation.navigations.AppBottomNavigation
import com.example.restaurantapp.presentation.navigations.BottomTabs
import com.example.restaurantapp.presentation.screens.detail_screen.DetailScreen
import com.example.restaurantapp.presentation.screens.detail_screen.DetailsScrenDestination
import com.example.restaurantapp.presentation.screens.detail_screen.DetailsViewModel
import com.example.restaurantapp.presentation.screens.search_screen.SearchScreen
import com.example.restaurantapp.presentation.screens.search_screen.SearchViewModel
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayScreen
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayViewModel

const val MAIN_NAV_GRAPH_ROUTE = "main_nav_graph_route"

@Composable
fun MainNavGraphRoot() {
    val navHostController = rememberNavController()
    Scaffold(
        bottomBar = {
            AppBottomNavigation(navController = navHostController)
        }
    ) { innerPaddings ->
        NavHost(
            modifier = Modifier.padding(innerPaddings),
            navController = navHostController,
            startDestination = BottomTabs.Home.route
        ) {
            composable(BottomTabs.Home.route) {
                val viewModel: TakeAwayViewModel = hiltViewModel()
                TakeAwayScreen(
                    uiStateFlow = viewModel.uiState,
                    navigateToDetailScreen = { menuId, categoryId ->
                        navHostController.navigate("${DetailsScrenDestination.route}/$menuId/$categoryId")
                    },
                    retryMenu = {},
                    navigateToSearchScreen = {
                        navHostController.navigate(BottomTabs.Search.route)
                    }
                )
            }
            composable(
                route = DetailsScrenDestination.routeWithArgs,
                arguments = DetailsScrenDestination.arguments
            ) { navBackStackEntry ->
                val menuId = navBackStackEntry.arguments?.getString(DetailsScrenDestination.menuId)
                    ?: String()
                val categoryId =
                    navBackStackEntry.arguments?.getString(DetailsScrenDestination.categoryId)
                        ?: String()
                val viewModel: DetailsViewModel = hiltViewModel()

                DetailScreen(
                    uiStateFlow = viewModel.uiState,
                    getFoodById = {
                        viewModel.findFoodByIdAndFetch(
                            foodId = menuId,
                            categoryId = categoryId,
                        )
                    }
                )
            }
            composable(BottomTabs.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    onValueChange = viewModel::onValueChange,
                    uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    navigateToDetailScreen = { menuId, categoryId ->
                        navHostController.navigate("${DetailsScrenDestination.route}/$menuId/$categoryId")
                    },
                    navigateToBack = {
                        navHostController.popBackStack()
                    }
                )
            }
            composable(BottomTabs.SHOPPING.route) {

            }
            composable(BottomTabs.SETTINGS.route) {

            }
        }
    }
}