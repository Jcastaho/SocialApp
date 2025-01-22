package com.straccion.socialapp.common.util

import com.straccion.socialapp.common.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

internal class IOSDispatcher: DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    override val io: CoroutineDispatcher
        get() = Dispatchers.Default
}

internal actual fun provideDispatcher(): DispatcherProvider = IOSDispatcher()