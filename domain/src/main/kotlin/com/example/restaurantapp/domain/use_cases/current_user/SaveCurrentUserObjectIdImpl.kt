package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.repository.CurrentUserRepository

class SaveCurrentUserObjectIdImpl(
    private val repository: CurrentUserRepository
) : SaveCurrentUserObjectId {
    override fun invoke(objectId: String) {
        repository.saveUserObjectId(objectId)
    }
}