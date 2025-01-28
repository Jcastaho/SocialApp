package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.post.domain.model.PostComment
import com.straccion.socialapp.post.domain.repository.PostCommentsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.straccion.socialapp.common.util.Result

class RemovePostCommentUseCase: KoinComponent {
    private val repository: PostCommentsRepository by inject()

    suspend operator fun invoke(postId: Long, commentId: Long): Result<PostComment?> {
        return repository.removeComment(postId = postId, commentId = commentId)
    }
}