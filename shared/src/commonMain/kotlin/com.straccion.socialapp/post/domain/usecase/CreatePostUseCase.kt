package com.straccion.socialapp.post.domain.usecase

import com.straccion.socialapp.common.domain.model.Post
import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.common.util.Constants.INVALID_INPUT_POST_CAPTION_MESSAGE
import com.straccion.socialapp.post.domain.repository.PostRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CreatePostUseCase: KoinComponent {
    private val repository by inject<PostRepository>()

    suspend operator fun invoke(
        caption: String,
        imageBytes: ByteArray
    ): Result<Post>{
        with(caption){
            if (isBlank() || length > 200){
                return Result.Error(message = INVALID_INPUT_POST_CAPTION_MESSAGE)
            }
        }
        return repository.createPost(caption = caption, imageBytes = imageBytes)
    }
}