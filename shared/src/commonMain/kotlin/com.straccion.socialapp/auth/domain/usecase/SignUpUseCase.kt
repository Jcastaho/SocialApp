package com.straccion.socialapp.auth.domain.usecase

import com.straccion.socialapp.auth.domain.model.AuthResultData
import com.straccion.socialapp.auth.domain.repository.AuthRepository
import com.straccion.socialapp.common.util.Result
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SignUpUseCase : KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): Result<AuthResultData> {
        if (name.isBlank() || name.length < 3){
            return Result.Error(
                message = "Nombre Invalido"
            )
        }
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
        return repository.signUp(name, email, password)
    }
}

