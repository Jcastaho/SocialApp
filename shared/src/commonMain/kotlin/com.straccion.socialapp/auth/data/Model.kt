package com.straccion.socialapp.auth.data

import kotlinx.serialization.Serializable

@Serializable
internal data class SignUpRequest(
    val name: String,
    val email: String,
    val password:String
)

@Serializable
internal data class SignInRequest(
    val email: String,
    val password:String
)


@Serializable
data class AuthResponse(
    val data: AuthResponseData? = null,
    val errorMessage:String? = null
)


@Serializable
data class AuthResponseData(
    val id: Int,
    val name: String,
    val bio: String,
    val avatar: String? = null,
    val token: String,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)