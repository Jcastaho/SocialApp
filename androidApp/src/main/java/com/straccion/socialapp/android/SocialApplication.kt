package com.straccion.socialapp.android

import android.app.Application
import com.straccion.socialapp.android.di.appModule
import com.straccion.socialapp.di.getSharedModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class SocialApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule + getSharedModules())
            androidContext(this@SocialApplication)
        }
    }
}