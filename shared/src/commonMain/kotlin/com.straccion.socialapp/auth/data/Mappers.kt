package com.straccion.socialapp.auth.data

import com.straccion.socialapp.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData {
    return AuthResultData(id, name, bio, avatar, token, followersCount, followingCount)
}