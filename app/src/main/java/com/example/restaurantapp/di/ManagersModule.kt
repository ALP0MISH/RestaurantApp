package com.example.restaurantapp.di

import com.example.restaurantapp.presentation.managers.GlobalNavigatorManager
import com.example.restaurantapp.presentation.managers.NavigatorManagerImpl
import com.example.restaurantapp.presentation.managers.toast.ShowToastUseCase
import com.example.restaurantapp.presentation.managers.toast.ToastManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ManagersModule {

    @Binds
    @Singleton
    fun bindNavigatorManager(
        implementation: NavigatorManagerImpl
    ): GlobalNavigatorManager

    @Binds
    @Singleton
    fun bindShowToastUseCase(
        implementation: ToastManager
    ): ShowToastUseCase


}