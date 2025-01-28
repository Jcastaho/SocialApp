package com.straccion.socialapp.di

import com.straccion.socialapp.acount.data.AccountApiService
import com.straccion.socialapp.acount.data.repository.ProfileRepositoryImpl
import com.straccion.socialapp.acount.domain.repository.ProfileRepository
import com.straccion.socialapp.acount.domain.usecase.GetProfileUseCase
import com.straccion.socialapp.acount.domain.usecase.UpdateProfileUseCase
import com.straccion.socialapp.auth.data.AuthRepositoryImpl
import com.straccion.socialapp.auth.data.AuthService
import com.straccion.socialapp.auth.domain.repository.AuthRepository
import com.straccion.socialapp.auth.domain.usecase.SignInUseCase
import com.straccion.socialapp.auth.domain.usecase.SignUpUseCase
import com.straccion.socialapp.common.data.remote.FollowsApiService
import com.straccion.socialapp.common.data.remote.PostApiService
import com.straccion.socialapp.common.util.provideDispatcher
import com.straccion.socialapp.follows.data.FollowsRepositoryImpl
import com.straccion.socialapp.follows.domain.FollowsRepository
import com.straccion.socialapp.follows.domain.usecase.FollowOrUnfollowUseCase
import com.straccion.socialapp.follows.domain.usecase.GetFollowableUsersUseCase
import com.straccion.socialapp.follows.domain.usecase.GetFollowsUseCase
import com.straccion.socialapp.post.data.PostRepositoryImpl
import com.straccion.socialapp.post.data.remote.PostCommentsApiService
import com.straccion.socialapp.post.data.repository.PostCommentsRepositoryImpl
import com.straccion.socialapp.post.domain.repository.PostCommentsRepository
import com.straccion.socialapp.post.domain.repository.PostRepository
import com.straccion.socialapp.post.domain.usecase.AddPostCommentUseCase
import com.straccion.socialapp.post.domain.usecase.CreatePostUseCase
import com.straccion.socialapp.post.domain.usecase.GetPostCommentsUseCase
import com.straccion.socialapp.post.domain.usecase.GetPostUseCase
import com.straccion.socialapp.post.domain.usecase.GetPostsUseCase
import com.straccion.socialapp.post.domain.usecase.GetUserPostsUseCase
import com.straccion.socialapp.post.domain.usecase.LikeOrDislikePostUseCase
import com.straccion.socialapp.post.domain.usecase.RemovePostCommentUseCase
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val postModule = module {
    factory { PostApiService() }
    factory { GetPostsUseCase() }
    factory { LikeOrDislikePostUseCase() }
    factory { GetUserPostsUseCase() }
    factory { GetPostUseCase() }
    factory { CreatePostUseCase() }

    single<PostRepository> { PostRepositoryImpl(get(), get(), get()) }
}

private val postCommentModule = module {
    factory { PostCommentsApiService() }
    factory { GetPostCommentsUseCase() }
    factory { AddPostCommentUseCase() }
    factory { RemovePostCommentUseCase() }

    single<PostCommentsRepository> { PostCommentsRepositoryImpl(get(), get(), get()) }
}

private val followsModule = module {
    factory { FollowsApiService() }
    factory { GetFollowableUsersUseCase() }
    factory { FollowOrUnfollowUseCase() }
    factory { GetFollowsUseCase() }

    single<FollowsRepository> { FollowsRepositoryImpl(get(), get(), get()) }
}

private val accountModule = module {
    factory { AccountApiService() }
    factory { GetProfileUseCase() }
    factory { UpdateProfileUseCase() }
    single<ProfileRepository> { ProfileRepositoryImpl(get(), get(), get()) }
}

fun getSharedModules() = listOf(
    platformModule,
    authModule,
    utilityModule,
    postModule,
    followsModule,
    accountModule,
    postCommentModule
)