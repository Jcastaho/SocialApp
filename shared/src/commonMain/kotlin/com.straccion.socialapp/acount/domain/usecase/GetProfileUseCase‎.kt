package com.straccion.socialapp.acount.domain.usecase

import com.straccion.socialapp.acount.domain.model.Profile
import com.straccion.socialapp.acount.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.straccion.socialapp.common.util.Result

class GetProfileUseCase : KoinComponent {
    private val repository: ProfileRepository by inject()
    operator fun invoke(profileId: Long): Flow<Result<Profile>> {
        return repository.getProfile(profileId = profileId)
    }
}