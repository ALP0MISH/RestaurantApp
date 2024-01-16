package com.example.restaurantapp.domain.use_cases.interactor

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.MenuRepository
import java.util.concurrent.CancellationException


private const val DEFAULT_ERROR_MESSAGE = "Something went wrong"

data class AllMenu(
    val allDrinks: List<MenuDomain>,
    val allDesserts: List<MenuDomain>,
    val allFastFoot: List<MenuDomain>,
    val allHotDishes: List<MenuDomain>,
    val allSalads: List<MenuDomain>,
)

class FetchMenuInteractorImpl(
    private val menuRepository: MenuRepository
) : FetchMenuInteractor {
    override suspend fun fetchAll(): Result<AllMenu> {
        return try {
            Result.Success(
                AllMenu(
                    allDrinks = menuRepository.fetchAllDrinks().data ?: emptyList(),
                    allDesserts = menuRepository.fetchAllDesserts().data ?: emptyList(),
                    allFastFoot = menuRepository.fetchAllFastFood().data ?: emptyList(),
                    allHotDishes = menuRepository.fetchAllHotDishes().data ?: emptyList(),
                    allSalads = menuRepository.fetchAllSalads().data ?: emptyList(),
                )
            )
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }

}