package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.post.domain.model.PostComment
import com.straccion.socialapp.post.domain.repository.PostCommentsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPostCommentsUseCase: KoinComponent {
    private val repository: PostCommentsRepository by inject()

    suspend operator fun invoke(
        postId: Long,
        page: Int,
        pageSize: Int
    ): Result<List<PostComment>> {
        return repository.getPostComments(postId, page, pageSize)
    }
}