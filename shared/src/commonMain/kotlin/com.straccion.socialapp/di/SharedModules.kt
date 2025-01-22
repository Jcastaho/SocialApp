package com.straccion.socialapp.di

import com.straccion.socialapp.auth.data.AuthRepositoryImpl
import com.straccion.socialapp.auth.data.AuthService
import com.straccion.socialapp.auth.domain.repository.AuthRepository
import com.straccion.socialapp.auth.domain.usecase.SignInUseCase
import com.straccion.socialapp.auth.domain.usecase.SignUpUseCase
import com.straccion.socialapp.common.util.provideDispatcher
import org.koin.dsl.module

private val authModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    factory { AuthService() }
    factory { SignUpUseCase() }
    factory { SignInUseCase() }
}

private val utilityModule = module { 
    factory { provideDispatcher() }
}

fun getSharedModules() = listOf(authModule, utilityModule)