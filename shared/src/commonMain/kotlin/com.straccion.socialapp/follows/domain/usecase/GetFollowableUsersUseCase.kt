package com.straccion.socialapp.follows.domain.usecase

import com.straccion.socialapp.common.domain.model.FollowsUser
import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.follows.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetFollowableUsersUseCase: KoinComponent {
    private val repository by inject<FollowsRepository>()

    suspend operator fun invoke(): Result<List<FollowsUser>> {
        return repository.getFollowableUsers()
    }
}