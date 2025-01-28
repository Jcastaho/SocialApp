package com.straccion.socialapp.follows.domain.usecase

import com.straccion.socialapp.common.domain.model.FollowsUser
import com.straccion.socialapp.follows.domain.FollowsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.straccion.socialapp.common.util.Result

class GetFollowsUseCase: KoinComponent {
    private val followsRepository: FollowsRepository by inject()

    suspend operator fun invoke(
        userId: Long,
        page: Int,
        pageSize: Int,
        followsType: Int
    ): Result<List<FollowsUser>> {
        return followsRepository.getFollows(userId, page, pageSize, followsType)
    }
}