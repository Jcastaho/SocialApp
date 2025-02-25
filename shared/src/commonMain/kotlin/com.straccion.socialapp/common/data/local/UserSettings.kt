package com.straccion.socialapp.common.data.local

import com.straccion.socialapp.auth.domain.model.AuthResultData
import kotlinx.serialization.Serializable

@Serializable
data class UserSettings(
    val id: Long = -1,
    val name: String = "",
    val bio: String = "",
    val imageUrl: String? = null,
    val token: String = "",
    val followersCount: Int = 0,
    val followingCount: Int = 0
)

fun UserSettings.toAuthResultData(): AuthResultData {
    return AuthResultData(
        id, name, bio, imageUrl, token, followersCount, followingCount
    )
}

fun AuthResultData.toUserSettings(): UserSettings {
    return UserSettings(
        id, name, bio, imageUrl, token, followersCount, followingCount
    )
}
