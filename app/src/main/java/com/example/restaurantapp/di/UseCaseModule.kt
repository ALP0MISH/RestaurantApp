package com.example.restaurantapp.di

import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.repository.MenuRepository
import com.example.restaurantapp.domain.repository.UserRepository
import com.example.restaurantapp.domain.use_cases.current_user.FetchAllUserUseCase
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.current_user.FetchCurrentUserUseCaseImpl
import com.example.restaurantapp.domain.use_cases.current_user.FetchUserUseCaseImpl
import com.example.restaurantapp.domain.use_cases.current_user.IsOnboardingPassedUseCase
import com.example.restaurantapp.domain.use_cases.current_user.IsOnboardingPassedUseCaseImpl
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCase
import com.example.restaurantapp.domain.use_cases.current_user.SaveCurrentUserUseCaseImpl
import com.example.restaurantapp.domain.use_cases.current_user.SetOnboardingShowedUseCase
import com.example.restaurantapp.domain.use_cases.current_user.SetOnboardingShowedUseCaseImpl
import com.example.restaurantapp.domain.use_cases.interactor.FetchMenuInteractor
import com.example.restaurantapp.domain.use_cases.interactor.FetchMenuInteractorImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchCategoriesUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchCategoriesUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchDessertsByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchDessertsByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchDrinksByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchDrinksByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchFastFoodByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchFastFoodByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchHotDishesByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchHotDishesByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchMenuByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.menu.FetchMenuByIdUserCase
import com.example.restaurantapp.domain.use_cases.menu.FetchSaladByIdUseCase
import com.example.restaurantapp.domain.use_cases.menu.FetchSaladByIdUseCaseImpl
import com.example.restaurantapp.domain.use_cases.signIn.SignInUseCase
import com.example.restaurantapp.domain.use_cases.signIn.SignInUseCaseImpl
import com.example.restaurantapp.domain.use_cases.signup.SignUpUseCase
import com.example.restaurantapp.domain.use_cases.signup.SignUpUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideSignInUseCase(
        repository: LoginRepository
    ): SignInUseCase = SignInUseCaseImpl(
        repository = repository
    )

    @Provides
    fun provideSignUpUseCase(
        repository: LoginRepository, userRepository: UserRepository
    ): SignUpUseCase = SignUpUseCaseImpl(
        repository = repository, userRepository = userRepository
    )

    @Provides
    fun provideSaveCurrentUserUseCase(
        repository: CurrentUserRepository
    ): SaveCurrentUserUseCase = SaveCurrentUserUseCaseImpl(
        repository = repository
    )

    @Provides
    fun provideFetchCurrentUserUseCase(
        repository: CurrentUserRepository
    ): FetchCurrentUserUseCase = FetchCurrentUserUseCaseImpl(
        repository = repository
    )

    @Provides
    fun provideIsOnboardingPassedUseCase(
        repository: CurrentUserRepository
    ): IsOnboardingPassedUseCase = IsOnboardingPassedUseCaseImpl(
        repository = repository
    )

    @Provides
    fun provideSetOnboardingShowedUseCase(
        repository: CurrentUserRepository
    ): SetOnboardingShowedUseCase = SetOnboardingShowedUseCaseImpl(
        repository = repository
    )

    @Provides
    fun provideFetchMenuInteractor(
        menuRepository: MenuRepository
    ): FetchMenuInteractor = FetchMenuInteractorImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchUserUseCase(
        repository: CurrentUserRepository
    ): FetchAllUserUseCase = FetchUserUseCaseImpl(
        currentUserRepository = repository
    )

    @Provides
    fun provideFetchMenuByIdUseCase(
        menuRepository: MenuRepository
    ): FetchMenuByIdUserCase = FetchMenuByIdUseCaseImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchDessertsByIdUseCase(
        menuRepository: MenuRepository
    ): FetchDessertsByIdUseCase = FetchDessertsByIdUseCaseImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchDrinksByIdUseCaseImpl(
        menuRepository: MenuRepository
    ): FetchDrinksByIdUseCase = FetchDrinksByIdUseCaseImpl(
        menuRepository = menuRepository
    )


    @Provides
    fun provideFetchFastFoodByIdUseCaseImpl(
        menuRepository: MenuRepository
    ): FetchFastFoodByIdUseCase = FetchFastFoodByIdUseCaseImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchHotDishesByIdUseCaseImpl(
        menuRepository: MenuRepository
    ): FetchHotDishesByIdUseCase = FetchHotDishesByIdUseCaseImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchSaladByIdUseCaseImpl(
        menuRepository: MenuRepository
    ): FetchSaladByIdUseCase = FetchSaladByIdUseCaseImpl(
        menuRepository = menuRepository
    )

    @Provides
    fun provideFetchCategoriesUseCase(
        menuRepository: MenuRepository
    ): FetchCategoriesUseCase = FetchCategoriesUseCaseImpl(
        menuRepository = menuRepository
    )

}