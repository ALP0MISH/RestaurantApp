package com.example.restaurantapp.domain.use_cases.current_user

import com.example.restaurantapp.domain.models.UserDomain

interface SaveCurrentUserObjectId {
    operator fun invoke(objectId: String)

}