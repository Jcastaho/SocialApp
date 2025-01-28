package com.straccion.socialapp.di

import com.straccion.socialapp.common.data.local.UserPreferences
import com.straccion.socialapp.common.data.IOSUserPreferences
import org.koin.dsl.module

actual val platformModule = module {
    single<UserPreferences> { IOSUserPreferences(get()) }

//    single {
//        createDatastore()
//    }
}