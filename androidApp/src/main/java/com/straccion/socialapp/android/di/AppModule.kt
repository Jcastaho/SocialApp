package com.straccion.socialapp.android.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.straccion.socialapp.android.MainActivityViewModel
import com.straccion.socialapp.android.auth.login.LoginViewModel
import com.straccion.socialapp.android.auth.signup.SignUpViewModel
import com.straccion.socialapp.android.coomon.datastore.UserSettingsSerializer
import com.straccion.socialapp.android.home.HomeScreenViewModel
import com.straccion.socialapp.android.post.PostDetailSreenViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { LoginViewModel(get(), get()) }
    viewModel { SignUpViewModel(get(), get()) }
    viewModel { MainActivityViewModel(get()) }
    viewModel { HomeScreenViewModel() }
    viewModel { PostDetailSreenViewModel() }

    single {
        DataStoreFactory.create(
            serializer = UserSettingsSerializer,
            produceFile = {
                androidContext().dataStoreFile(
                    fileName = "app_user_settings"
                )
            }
        )
    }

}