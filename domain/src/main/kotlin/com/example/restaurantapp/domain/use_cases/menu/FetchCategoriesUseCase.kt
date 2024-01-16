package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain

interface FetchCategoriesUseCase {
    suspend operator fun invoke(
        foodId: String,
    ): Result<List<CategoryDomain>>
}