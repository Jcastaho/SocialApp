package com.straccion.socialapp.android.di

import com.straccion.socialapp.android.MainActivityViewModel
import com.straccion.socialapp.android.account.edit.EditProfileViewModel
import com.straccion.socialapp.android.account.follows.FollowsViewModel
import com.straccion.socialapp.android.account.profile.ProfileViewModel
import com.straccion.socialapp.android.auth.login.LoginViewModel
import com.straccion.socialapp.android.auth.signup.SignUpViewModel
import com.straccion.socialapp.android.coomon.util.ImageBytesReader
import com.straccion.socialapp.android.home.HomeScreenViewModel
import com.straccion.socialapp.android.post.PostDetailViewModel
import com.straccion.socialapp.android.post.create_post.CreatePostViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { MainActivityViewModel(get())}
    viewModel { HomeScreenViewModel(get(), get(), get(), get())}
    viewModel { PostDetailViewModel(get(), get(), get(), get(), get())}
    viewModel { ProfileViewModel(get(), get(), get(), get()) }
    viewModel { EditProfileViewModel(get(), get(), get()) }
    viewModel { FollowsViewModel(get()) }
    single { ImageBytesReader(androidContext()) }
    viewModel { CreatePostViewModel(get(), get()) }



}