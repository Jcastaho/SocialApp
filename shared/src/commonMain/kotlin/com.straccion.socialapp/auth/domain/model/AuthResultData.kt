package com.straccion.socialapp.auth.domain.model

data class AuthResultData(
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String? = null,
    val token: String,
    val followersCount: Int = 0,
    val followingCount: Int = 0
)
