package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.post.domain.model.PostComment
import com.straccion.socialapp.post.domain.repository.PostCommentsRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.straccion.socialapp.common.util.Result

class AddPostCommentUseCase: KoinComponent {
    private val repository: PostCommentsRepository by inject()

    suspend operator fun invoke(postId: Long, content: String): Result<PostComment> {
        if (content.isBlank()){
            return Result.Error(message = "Comment content cannot be empty")
        }
        return repository.addComment(postId = postId, content = content)
    }
}