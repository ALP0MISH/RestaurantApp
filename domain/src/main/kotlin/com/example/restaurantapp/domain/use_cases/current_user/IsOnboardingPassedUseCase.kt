package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface IsOnboardingPassedUseCase {
    operator fun invoke(): Boolean
}

class IsOnboardingPassedUseCaseImpl(
    private val repository: CurrentUserRepository
): IsOnboardingPassedUseCase{
    override fun invoke(): Boolean {
        return repository.isOnboardingPassed()
    }

}