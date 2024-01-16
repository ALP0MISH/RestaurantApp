package com.example.restaurantapp.presentation.app

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val navigatorManager: GlobalNavigatorManager,
) : ViewModel() {
    val destinationsFlow = navigatorManager.destinationsFlow()
}