package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface ClearOnBoardingUseCase {
    operator fun invoke()
}

class ClearOnBoardingUseCaseImpl(
    private val repository: CurrentUserRepository
) : ClearOnBoardingUseCase {
    override fun invoke() {
        return repository.clearOnBoarding()
    }
}