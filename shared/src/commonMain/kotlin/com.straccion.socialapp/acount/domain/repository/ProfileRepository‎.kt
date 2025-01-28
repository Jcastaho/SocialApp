package com.straccion.socialapp.acount.domain.repository

import com.straccion.socialapp.acount.domain.model.Profile
import com.straccion.socialapp.common.util.Result
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    fun getProfile(profileId: Long): Flow<Result<Profile>>
    suspend fun updateProfile(profile: Profile, imageBytes: ByteArray?): Result<Profile>
}