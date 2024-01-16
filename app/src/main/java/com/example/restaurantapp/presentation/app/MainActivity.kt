package com.example.restaurantapp.presentation.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.restaurantapp.presentation.screens.detail_screen.DetailScreen
import com.example.restaurantapp.presentation.theme.RestaurantAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //DetailScreen()
            RestaurantAppTheme {
                RestaurantComposeApp(destinationFlow = viewModel.destinationsFlow)
            }
        }
    }
}