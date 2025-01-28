package com.straccion.socialapp.auth.domain.repository

import com.straccion.socialapp.auth.domain.model.AuthResultData
import com.straccion.socialapp.common.util.Result

internal interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData>

    suspend fun signIn(email: String, password: String): Result<AuthResultData>
}