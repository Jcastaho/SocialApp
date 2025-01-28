package com.straccion.socialapp.acount.data.model

import com.straccion.socialapp.acount.domain.model.Profile
import com.straccion.socialapp.common.data.local.UserSettings

fun UserSettings.toDomainProfile(): Profile {
    return Profile(
        id = id,
        name = name,
        bio = bio,
        imageUrl = imageUrl,
        followersCount = followersCount,
        followingCount = followingCount,
        isFollowing = false,
        isOwnProfile = true
    )
}

fun Profile.toUserSettings(token: String): UserSettings{
    return UserSettings(
        id = id,
        name = name,
        bio = bio,
        imageUrl = imageUrl,
        followersCount = followersCount,
        followingCount = followingCount,
        token = token
    )
}