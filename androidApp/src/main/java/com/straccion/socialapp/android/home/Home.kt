package com.straccion.socialapp.android.home

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.destinations.PostDetailDestination
import com.straccion.socialapp.android.destinations.ProfileDestination
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun Home(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsFeedUiState = viewModel.postsFeedUiState,
        homeRefreshState = viewModel.homeRefreshState,
        onUiAction = {viewModel.onUiAction(it)},
        onProfileNavigation = {navigator.navigate(ProfileDestination(it))},
        onPostDetailNavigation = {navigator.navigate(PostDetailDestination(it.postId))}
    )
}