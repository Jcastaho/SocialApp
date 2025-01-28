package com.straccion.socialapp.post.data

import com.straccion.socialapp.common.data.local.UserPreferences
import com.straccion.socialapp.common.data.local.UserSettings
import com.straccion.socialapp.common.data.model.LikeParams
import com.straccion.socialapp.common.data.model.NewPostParams
import com.straccion.socialapp.common.data.model.PostsApiResponse
import com.straccion.socialapp.common.data.remote.PostApiService
import com.straccion.socialapp.common.domain.model.Post
import com.straccion.socialapp.common.util.Constants
import com.straccion.socialapp.common.util.DispatcherProvider
import com.straccion.socialapp.common.util.Result
import com.straccion.socialapp.common.util.safeApiCall
import com.straccion.socialapp.post.domain.repository.PostRepository
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okio.IOException

internal class PostRepositoryImpl(
    private val postApiService: PostApiService,
    private val userPreferences: UserPreferences,
    private val dispatcher: DispatcherProvider
) : PostRepository {
    override suspend fun getFeedPosts(page: Int, pageSize: Int): Result<List<Post>> {
        return fetchPosts(
            apiCall = { currentUserData ->
                postApiService.getFeedPosts(
                    userToken = currentUserData.token,
                    currentUserId = currentUserData.id,
                    page = page,
                    pageSize = pageSize
                )
            }
        )
    }

    override suspend fun likeOrDislikePost(postId: Long, shouldLike: Boolean): Result<Boolean> {
        return withContext(dispatcher.io) {
            try {
                val userData = userPreferences.getUserData()
                val likeParams = LikeParams(postId = postId, userId = userData.id)

                val apiResponse = if (shouldLike){
                    postApiService.likePost(userData.token, likeParams)
                }else{
                    postApiService.dislikePost(userData.token, likeParams)
                }

                if (apiResponse.code == HttpStatusCode.OK) {
                    Result.Success(data = apiResponse.data.success)
                } else {
                    Result.Error(data = false, message = "${apiResponse.data.message}")
                }
            } catch (ioException: IOException) {
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (exception: Throwable) {
                Result.Error(
                    message = "${exception.message}"
                )
            }
        }
    }

    override suspend fun getUserPosts(userId: Long, page: Int, pageSize: Int): Result<List<Post>> {
        return fetchPosts(
            apiCall = { currentUserData ->
                postApiService.getUserPosts(
                    token = currentUserData.token,
                    userId = userId,
                    currentUserId = currentUserData.id,
                    page = page,
                    pageSize = pageSize
                )
            }
        )
    }

    private suspend fun fetchPosts(
        apiCall: suspend (UserSettings) -> PostsApiResponse
    ): Result<List<Post>>{
        return withContext(dispatcher.io){
            try {
                val currentUserData = userPreferences.getUserData()
                val apiResponse = apiCall(currentUserData)

                when (apiResponse.code) {
                    HttpStatusCode.OK -> {
                        Result.Success(data = apiResponse.data.posts.map { it.toDomainPost() })
                    }
                    else -> {
                        Result.Error(message = Constants.UNEXPECTED_ERROR)
                    }
                }
            }catch (ioException: IOException) {
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            } catch (exception: Throwable) {
                Result.Error(message = "${exception.cause}")
            }
        }
    }

    override suspend fun getPost(postId: Long): Result<Post> {
        return withContext(dispatcher.io){
            try {
                val userData = userPreferences.getUserData()

                val apiResponse = postApiService.getPost(
                    token = userData.token,
                    currentUserId = userData.id,
                    postId = postId
                )

                if (apiResponse.code == HttpStatusCode.OK){
                    Result.Success(data = apiResponse.data.post!!.toDomainPost())
                }else{
                    Result.Error(message = apiResponse.data.message!!)
                }
            }catch (ioException: IOException){
                Result.Error(message = Constants.NO_INTERNET_ERROR)
            }catch (exception: Throwable){
                Result.Error(message = Constants.UNEXPECTED_ERROR)
            }
        }
    }

    override suspend fun createPost(caption: String, imageBytes: ByteArray): Result<Post> {
        return safeApiCall(dispatcher){
            val currentUserData = userPreferences.getUserData()

            val postData = Json.encodeToString(
                serializer = NewPostParams.serializer(),
                value = NewPostParams(caption = caption, userId = currentUserData.id)
            )

            val apiResponse = postApiService.createPost(
                token = currentUserData.token,
                newPostData = postData,
                imageBytes = imageBytes
            )

            if (apiResponse.code == HttpStatusCode.OK){
                Result.Success(data = apiResponse.data.post!!.toDomainPost())
            }else{
                Result.Error(message = apiResponse.data.message ?: Constants.UNEXPECTED_ERROR)
            }
        }
    }
}