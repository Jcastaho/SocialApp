package com.straccion.socialapp.android.post

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.destinations.ProfileDestination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun PostDetail(
    navigator: DestinationsNavigator,
    postId: Long
) {
    val viewModel: PostDetailViewModel = koinViewModel()

    PostDetailScreen(
        postUiState = viewModel.postUiState,
        commentsUiState = viewModel.commentsUiState,
        postId = postId,
        onProfileNavigation = {
            navigator.navigate(ProfileDestination(it))
        },
        onUiAction = viewModel::onUiAction
    )
}