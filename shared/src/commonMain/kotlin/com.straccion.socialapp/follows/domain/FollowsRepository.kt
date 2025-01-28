package com.straccion.socialapp.follows.domain

import com.straccion.socialapp.common.domain.model.FollowsUser
import com.straccion.socialapp.common.util.Result

interface FollowsRepository {
    suspend fun getFollowableUsers():Result<List<FollowsUser>>
    suspend fun followOrUnfollow(followedUserId: Long, shouldFollow: Boolean): Result<Boolean>
    suspend fun getFollows(userId: Long, page: Int, pageSize: Int, followsType: Int): Result<List<FollowsUser>>
}