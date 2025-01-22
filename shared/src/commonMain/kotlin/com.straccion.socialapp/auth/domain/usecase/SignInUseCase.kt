package com.straccion.socialapp.auth.domain.usecase

import com.straccion.socialapp.auth.domain.model.AuthResultData
import com.straccion.socialapp.auth.domain.repository.AuthRepository
import com.straccion.socialapp.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignInUseCase: KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<AuthResultData> {
        if (email.isBlank() || "@" !in email){
            return Result.Error(
                message = "Correo Invalido"
            )
        }
        if (password.isBlank() || password. length < 4){
            return Result.Error(
                message = "Contraseña Invalido"
            )
        }
        return repository.signIn(email, password)
    }
}