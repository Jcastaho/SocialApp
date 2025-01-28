package com.straccion.socialapp.follows.domain.usecase

import com.straccion.socialapp.follows.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.straccion.socialapp.common.util.Result

class FollowOrUnfollowUseCase: KoinComponent {
    private val repository by inject<FollowsRepository>()

    suspend operator fun invoke(
        followedUserId: Long,
        shouldFollow: Boolean
    ): Result<Boolean> {
        return repository.followOrUnfollow(followedUserId, shouldFollow)
    }
}