package com.straccion.socialapp.acount.domain.usecase

import com.straccion.socialapp.acount.domain.model.Profile
import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.acount.domain.repository.ProfileRepository
import com.straccion.socialapp.common.util.Constants.INVALID_INPUT_BIO_MESSAGE
import com.straccion.socialapp.common.util.Constants.INVALID_INPUT_NAME_MESSAGE
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UpdateProfileUseCase: KoinComponent {
    private val profileRepository: ProfileRepository by inject()

    suspend operator fun invoke(profile: Profile, imageBytes: ByteArray?): Result<Profile> {
        with(profile.name){
            if (isBlank() || length < 3 || length > 20){
                return Result.Error(message = INVALID_INPUT_NAME_MESSAGE)
            }
        }
        with(profile.bio){
            if (isBlank() || length > 150){
                return Result.Error(message = INVALID_INPUT_BIO_MESSAGE)
            }
        }
        return profileRepository.updateProfile(profile, imageBytes)
    }
}