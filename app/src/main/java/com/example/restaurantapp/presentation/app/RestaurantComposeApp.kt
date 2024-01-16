package com.example.restaurantapp.presentation.app

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.restaurantapp.presentation.navigations.AppNavGraph
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RestaurantComposeApp(
    destinationFlow: Flow<Pair<String, Boolean>>,
    modifier: Modifier = Modifier
) {
    val navHostController = rememberNavController()

    val (destination, isClearBackstack) = destinationFlow.collectAsState(initial = "" to false).value
    if (destination.isNotEmpty()) {
        navHostController.navigate(destination) {
            if (isClearBackstack) popUpTo(0)
        }
    }
    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {
        AppNavGraph(navController = navHostController)
    }
}