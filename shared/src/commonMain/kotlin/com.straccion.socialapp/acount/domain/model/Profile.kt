package com.straccion.socialapp.acount.domain.model

data class Profile(
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String? = null,
    val followersCount: Int = 0,
    val followingCount: Int = 0,
    val isFollowing: Boolean = false,
    val isOwnProfile: Boolean = false
)