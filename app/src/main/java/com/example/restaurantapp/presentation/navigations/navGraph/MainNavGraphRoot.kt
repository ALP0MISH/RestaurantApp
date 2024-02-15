package com.example.restaurantapp.presentation.navigations.navGraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.presentation.navigations.AppBottomNavigation
import com.example.restaurantapp.presentation.navigations.BottomTabs
import com.example.restaurantapp.presentation.screens.basket_screen.BasketScreen
import com.example.restaurantapp.presentation.screens.basket_screen.BasketViewModel
import com.example.restaurantapp.presentation.screens.detail_screen.DetailScreen
import com.example.restaurantapp.presentation.screens.detail_screen.DetailsScreenDestination
import com.example.restaurantapp.presentation.screens.detail_screen.DetailsScreenDestination.categoryId
import com.example.restaurantapp.presentation.screens.detail_screen.DetailsViewModel
import com.example.restaurantapp.presentation.screens.edit_profile.EditProfileScreen
import com.example.restaurantapp.presentation.screens.edit_profile.EditProfileViewModel
import com.example.restaurantapp.presentation.screens.search_screen.SearchScreen
import com.example.restaurantapp.presentation.screens.search_screen.SearchViewModel
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayScreen
import com.example.restaurantapp.presentation.screens.take_away_screen.TakeAwayViewModel

const val MAIN_NAV_GRAPH_ROUTE = "main_nav_graph_route"

@Composable
fun MainNavGraphRoot() {
    val listItems = listOf(
        BottomTabs.Home,
        BottomTabs.SEARCH,
        BottomTabs.SHOPPING,
        BottomTabs.SETTINGS,
    )
    val navHostController = rememberNavController()
    val rememberItems = remember { listItems }
    val controller = currentRoute(navController = navHostController)
    Scaffold(
        bottomBar = {
            AppBottomNavigation(
                navController = navHostController,
                items = rememberItems,
                currentRoute = controller
            )
        },
    ) { innerPaddings ->
        NavHost(
            modifier = Modifier.padding(innerPaddings),
            navController = navHostController,
            startDestination = BottomTabs.Home.route
        ) {
            composable(BottomTabs.Home.route) {
                val viewModel: TakeAwayViewModel = hiltViewModel()
                val basketViewModel: BasketViewModel = hiltViewModel()
                TakeAwayScreen(
                    uiStateFlow = viewModel.uiState,
                    navigateToDetailScreen = { menuId, categoryId ->
                        navHostController.navigate("${DetailsScreenDestination.route}/$menuId/$categoryId")
                    },
                    retryMenu = {
                        viewModel.fetchMenu()
                    },
                    navigateToSearchScreen = {
                        navHostController.navigate(BottomTabs.SEARCH.route)
                    },
                    addToBasket = basketViewModel::addToBasket,
                    navigateToBasketScreen = {
                        navHostController.navigate(BottomTabs.SHOPPING.route)
                    },
                    navigateToEditScreen = {
                        navHostController.navigate(BottomTabs.SETTINGS.route)
                    }
                )
            }
            composable(
                route = DetailsScreenDestination.getRouteWithArgs(categoryId),
                arguments = DetailsScreenDestination.arguments
            ) { navBackStackEntry ->
                val menuId = navBackStackEntry.arguments?.getString(DetailsScreenDestination.menuId)
                    ?: String()
                val categoryId =
                    navBackStackEntry.arguments?.getString(DetailsScreenDestination.categoryId)
                        ?: String()
                val viewModel: DetailsViewModel = hiltViewModel()
                val basketViewModel: BasketViewModel = hiltViewModel()

                DetailScreen(
                    uiStateFlow = viewModel.uiState,
                    getFoodById = {
                        viewModel.findFoodByIdAndFetch(
                            foodId = menuId,
                            categoryId = categoryId,
                        )
                    },
                    refreshClick = {
                        viewModel.findFoodByIdAndFetch(
                            foodId = menuId,
                            categoryId = categoryId,
                        )
                    },
                    navigateToBasketScreen = {
                        navHostController.navigate(BottomTabs.SHOPPING.route)

                    },
                    addToBasket = basketViewModel::addToBasket
                )
            }
            composable(BottomTabs.SEARCH.route) {
                val viewModel: SearchViewModel = hiltViewModel()
                SearchScreen(
                    onValueChange = viewModel::onValueChange,
                    uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    navigateToDetailScreen = { menuId, categoryId ->
                        navHostController.navigate("${DetailsScreenDestination.route}/$menuId/$categoryId")
                    },
                )
            }
            composable(BottomTabs.SHOPPING.route) {
                val viewModel: BasketViewModel = hiltViewModel()
                BasketScreen(
                    uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    navigateToDetailScreen = { menuId ->
                        navHostController.navigate("${DetailsScreenDestination.route}/$menuId/$categoryId")
                    },
                    refreshClick = { viewModel.fetchFromBasket() }
                )
            }
            composable(BottomTabs.SETTINGS.route) {
                val viewModel: EditProfileViewModel = hiltViewModel()
                EditProfileScreen(
                    uiState = viewModel.uiState,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavController): String {
    return rememberSaveable(navController) {
        navController.currentBackStackEntry?.destination?.route ?: ""
    }
}