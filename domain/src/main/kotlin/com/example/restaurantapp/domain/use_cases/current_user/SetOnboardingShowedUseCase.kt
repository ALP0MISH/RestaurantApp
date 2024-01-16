package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.repository.CurrentUserRepository

interface SetOnboardingShowedUseCase {
    operator fun invoke()
}
class SetOnboardingShowedUseCaseImpl(
    private val repository: CurrentUserRepository
): SetOnboardingShowedUseCase{
    override fun invoke() {
        return repository.setOnboardingShowed()
    }
}