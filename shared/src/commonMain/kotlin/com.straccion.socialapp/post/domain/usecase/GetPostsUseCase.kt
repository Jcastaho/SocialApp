package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.common.domain.model.Post
import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPostsUseCase: KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Result<List<Post>> {
        return repository.getFeedPosts(page, pageSize)
    }
}