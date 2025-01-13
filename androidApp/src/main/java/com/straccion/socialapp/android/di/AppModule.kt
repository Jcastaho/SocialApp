package com.straccion.socialapp.android.di

import com.straccion.socialapp.android.auth.login.LoginViewModel
import com.straccion.socialapp.android.auth.signup.SignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel() }
    viewModel { SignUpViewModel() }
}