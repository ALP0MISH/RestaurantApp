package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.CategoryDomain
import com.example.restaurantapp.domain.repository.MenuRepository
import java.util.concurrent.CancellationException

class FetchCategoriesUseCaseImpl(
    private val menuRepository: MenuRepository
) : FetchCategoriesUseCase {
    override suspend fun invoke(
        foodId: String
    ): Result<List<CategoryDomain>> {
        return try {
            menuRepository.getCategories(
                foodId = foodId,
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(e.stackTraceToString())
        }
    }
}