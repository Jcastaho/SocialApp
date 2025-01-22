package com.straccion.socialapp.android.home.onboarding

import com.straccion.socialapp.android.coomon.fake_data.FollowsUser

data class OnBoardingUiState(
    val isLoading: Boolean = false,
    val users: List<FollowsUser> = listOf(),
    val errorMessage: String? = null,
    val shouldShowOnBoarding: Boolean = false
)
