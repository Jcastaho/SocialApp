package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.common.domain.model.Post
import com.straccion.socialapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import com.straccion.socialapp.common.util.Result
import org.koin.core.component.inject

class GetPostUseCase : KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(postId: Long): Result<Post> {
        return repository.getPost(postId = postId)
    }
}