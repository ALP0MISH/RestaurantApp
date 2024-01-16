package com.example.restaurantapp.domain.use_cases.interactor

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain
import com.example.restaurantapp.domain.models.MenuDomain

interface FetchMenuInteractor {
    suspend fun fetchAll(): Result<AllMenu>
}