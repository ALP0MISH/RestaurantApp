package com.example.restaurantapp.presentation.screens.onboarding

import androidx.lifecycle.ViewModel
import com.example.restaurantapp.domain.use_cases.current_user.SetOnboardingShowedUseCase
import com.example.restaurantapp.presentation.auth.login.LoginDestination
import com.example.restaurantapp.presentation.extentions.createMutableSharedFlowAsSingleLiveEvent
import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val setOnboardingShowedUserCase: SetOnboardingShowedUseCase,
) : ViewModel() {

    private val _navControllerFlow = createMutableSharedFlowAsSingleLiveEvent<String>()
    val navControllerFlow: SharedFlow<String> = _navControllerFlow.asSharedFlow()

    fun onboardingFinished() {
        setOnboardingShowedUserCase()
        _navControllerFlow.tryEmit(LoginDestination.route())
    }
}