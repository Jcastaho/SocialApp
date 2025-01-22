package com.straccion.socialapp.android.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.coomon.fake_data.Post
import com.straccion.socialapp.android.coomon.fake_data.sampleUsers
import com.straccion.socialapp.android.destinations.PostDetailDestination
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
@Composable
fun Home(
    navigator: DestinationsNavigator
) {
    val viewModel: HomeScreenViewModel = koinViewModel()

    HomeScreen(
        onBoardingUiState = viewModel.onBoardingUiState,
        postsUiState = viewModel.postsUiState,
        onPostClick = {
            navigator.navigate(PostDetailDestination(it.id))
        },
        onProfileClick = { },
        onLikeClick = { },
        onCommentClick = { },
        onFollowButtonClick = { _, _ -> },
        onBoardingFinish = { },
        fetchMoreData = {
            viewModel.fetchData()
        }
    )
}