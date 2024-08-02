package com.example.shopfeeap_mvvm.domain.use_case

import com.example.shopfeeap_mvvm.common.Resource
import com.example.shopfeeap_mvvm.data.remote.dto.toDrinkModel
import com.example.shopfeeap_mvvm.data.remote.request.AuthRequest
import com.example.shopfeeap_mvvm.domain.model.AuthResult
import com.example.shopfeeap_mvvm.domain.model.drink.DrinkModel
import com.example.shopfeeap_mvvm.domain.repository.AuthRepository
import com.example.shopfeeap_mvvm.domain.repository.DrinkRepository
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): AuthResult {
        val emailError = if (email.isBlank()) "Username error" else null
        val passError = if (email.isBlank()) "Password error" else null
        if (emailError != null){
            return AuthResult(emailError = emailError)
        }
        if (passError != null){
            return AuthResult(passwordError = password)
        }
        val loginRequest = AuthRequest(
            email = email.trim(),
            password = password.trim()
        )
        return AuthResult(
            result = repository.login(loginRequest)
        )
    }
}

