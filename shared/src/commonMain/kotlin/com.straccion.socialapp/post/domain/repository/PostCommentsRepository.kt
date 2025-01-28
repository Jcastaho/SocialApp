package com.straccion.socialapp.post.domain.repository

import com.straccion.socialapp.post.domain.model.PostComment
import com.straccion.socialapp.common.util.Result

internal interface PostCommentsRepository {
    suspend fun getPostComments(postId: Long, page: Int, pageSize: Int): Result<List<PostComment>>
    suspend fun addComment(postId: Long, content: String): Result<PostComment>
    suspend fun removeComment(postId: Long, commentId: Long): Result<PostComment?>
}