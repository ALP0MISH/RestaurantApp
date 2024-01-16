package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.MenuRepository
import java.util.concurrent.CancellationException

class FetchFastFoodByIdUseCaseImpl(
    private val menuRepository: MenuRepository
) : FetchFastFoodByIdUseCase {
    override suspend operator fun invoke(objectId: String): Result<MenuDomain> {
        return try {
            menuRepository.fetchFastFoodById(objectId)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(e.stackTraceToString())
        }
    }
}