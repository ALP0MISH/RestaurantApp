package com.example.restaurantapp.presentation.screens.select_category

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.presentation.auth.login.LoginDestination
import com.example.restaurantapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class SelectCategoryViewModel @Inject constructor(
    private val navigatorManager: GlobalNavigatorManager
) : ViewModel() {

    fun onTakeAwayClick() {
        navigatorManager.navigateTo(LoginDestination.route())
    }
}