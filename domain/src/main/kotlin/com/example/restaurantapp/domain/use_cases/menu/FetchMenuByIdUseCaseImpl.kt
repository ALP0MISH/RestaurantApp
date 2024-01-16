package com.example.restaurantapp.domain.use_cases.menu

import com.example.restaurantapp.domain.models.MenuDomain
import com.example.restaurantapp.domain.repository.MenuRepository
import java.util.concurrent.CancellationException

class FetchMenuByIdUseCaseImpl(
    private val menuRepository: MenuRepository
) : FetchMenuByIdUserCase {
    override suspend fun fetchSaladsById(objectId: String): MenuDomain {
        return try {
            menuRepository.fetchSaladsById(objectId).data ?: MenuDomain.unknown
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MenuDomain.unknown
        }
    }

    override suspend fun fetchDessertsById(objectId: String): MenuDomain {
        return try {
            menuRepository.fetchDessertsById(objectId).data ?: MenuDomain.unknown
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MenuDomain.unknown
        }
    }

    override suspend fun fetchFastFoodById(objectId: String): MenuDomain {
        return try {
            menuRepository.fetchFastFoodById(objectId).data ?: MenuDomain.unknown
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MenuDomain.unknown
        }
    }

    override suspend fun fetchDrinksById(objectId: String): MenuDomain {
        return try {
            menuRepository.fetchDrinksById(objectId).data ?: MenuDomain.unknown
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MenuDomain.unknown
        }
    }

    override suspend fun fetchHotDishesByID(objectId: String): MenuDomain {
        return try {
            menuRepository.fetchHotDishesByID(objectId).data ?: MenuDomain.unknown
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            MenuDomain.unknown
        }
    }
}