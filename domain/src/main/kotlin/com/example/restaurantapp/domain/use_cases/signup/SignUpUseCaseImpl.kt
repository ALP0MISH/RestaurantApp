package com.example.restaurantapp.domain.use_cases.signup

import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain
import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.repository.UserRepository

class SignUpUseCaseImpl(
    private val repository: LoginRepository,
    private val userRepository: UserRepository
) : SignUpUseCase {
    override suspend fun invoke(
        email: String,
        lastName: String,
        name: String,
        password: String
    ): Result<UserDomain> {
        if (email.isEmpty()) {
            return Result.Error(message = "First fill in email")
        }
        if (name.isEmpty()) {
            return Result.Error(message = "First fill in name")
        }
        if (lastName.isEmpty()) {
            return Result.Error(message = "First fill in lastName")
        }
        if (password.length < 8) {
            return Result.Error(message = "Incorrect fill password")
        }
        val response = repository.signUp(
            email = email,
            password = password,
            name = name,
            lastName = lastName
        )
        return userRepository.fetchUserById(response.data?.id ?: String())
    }
}