package com.example.restaurantapp.domain.use_cases.signIn

import com.example.restaurantapp.domain.repository.LoginRepository
import com.example.restaurantapp.domain.common.Result
import com.example.restaurantapp.domain.models.UserDomain
import java.util.regex.Pattern

class SignInUseCaseImpl constructor(
    private val repository: LoginRepository
) : SignInUseCase {
    override suspend fun invoke(
        email: String,
        password: String
    ): Result<UserDomain> {
//        if (email.isEmpty()) {
//            return Result.Error(message = "First fill in email")
//        }
        if (password.isEmpty()) {
            return Result.Error(message = "First fill in password")
        }
//        if (email.isValidString()) {
//            return Result.Error(message = "Incorrect fill email")
//        }
        if (password.length < 6) {
            return Result.Error("Incorrect password")
        }
        return repository.signIn(
            email = email,
            password = password
        )
    }
}

internal fun String.isValidString(): Boolean {
    val emailPattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}\\@[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}(\\.[a" +
                "-zA-Z0-9][a-zA-Z0-9\\-]{0,25})+"
    )
    return emailPattern.matcher(this).matches()
}