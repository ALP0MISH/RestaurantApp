package com.example.restaurantapp.domain.use_cases.signIn

import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain
import java.util.regex.Pattern

class SignInUseCaseImpl(
    private val repository: LoginRepository
) : SignInUseCase {
    override suspend fun invoke(
        email: String,
        password: String
    ): Result<UserDomain> {
        if (email.isEmpty()) {
            return Result.Error(message = "First fill in email")
        }
        if (!email.endsWith("@gmail.com")) {
            return Result.Error(message = "Email must end with @gmail.com")
        }
        if (password.isEmpty()) {
            return Result.Error(message = "First fill in password")
        }
        if (password.length < 6) {
            return Result.Error("Incorrect password")
        }
        return repository.signIn(
            email = email,
            password = password
        )
    }
}