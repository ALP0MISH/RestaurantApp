package com.example.restaurantapp.di

import com.example.restaurantapp.domain.repository.CurrentUserRepository
import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.repository.MenuRepository
import com.example.restaurantapp.domain.repository.UserRepository
import com.example.socialapp.data.repositories.CurrentUserRepositoryImpl
import com.example.socialapp.data.repositories.LoginRepositoryImpl
import com.example.socialapp.data.repositories.MenuRepositoryImpl
import com.example.socialapp.data.repositories.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindLoginRepository(
        impl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun bindCurrentUserRepository(
        impl: CurrentUserRepositoryImpl
    ): CurrentUserRepository

    @Binds
    fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository

    @Binds
    fun bindMenuRepository(
        impl: MenuRepositoryImpl
    ): MenuRepository

}