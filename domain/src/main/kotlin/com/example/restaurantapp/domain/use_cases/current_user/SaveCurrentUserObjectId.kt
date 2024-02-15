package com.example.restaurantapp.domain.use_cases.current_user

interface SaveCurrentUserObjectId {
    operator fun invoke(objectId: String)

}