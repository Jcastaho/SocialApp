package com.straccion.socialapp.android.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.rememberSwipeableState
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.components.PostListItem
import com.straccion.socialapp.android.coomon.fake_data.FollowsUser
import com.straccion.socialapp.android.coomon.fake_data.Post
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.home.onboarding.OnBoardingSection
import com.straccion.socialapp.android.home.onboarding.OnBoardingUiState


@OptIn(ExperimentalWearMaterialApi::class)
//@Destination(start = true)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onBoardingUiState: OnBoardingUiState,
    postsUiState: PostsUiState,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onFollowButtonClick: (Boolean, FollowsUser) -> Unit,
    onBoardingFinish: () -> Unit,
    fetchMoreData: () -> Unit
) {

    var isRefreshing by remember { mutableStateOf(false) }
    val swipeableState = rememberSwipeableState(initialValue = 0f)
    val listState = rememberLazyListState()
    val shouldFetchMorePosts by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo

            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount)
            }
        }
    }

    // Umbral para activar el gesto de refrescar
    val pullRefreshThreshold = 5f

    // Activa el refresco cuando se deslice hacia abajo más allá del umbral
    LaunchedEffect(Unit) {
        if (swipeableState.isAnimationRunning) {
            isRefreshing = true
            fetchMoreData() // Realiza la carga de datos o la acción de refresco
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()

    ) {



        LazyColumn(
            modifier = modifier
                .fillMaxSize(),
            state = listState
        ) {
            if (onBoardingUiState.shouldShowOnBoarding) {
                item(key = "onboardingsection") {
                    OnBoardingSection(
                        users = onBoardingUiState.users,
                        onUserClick = { onProfileClick(it.id) },
                        onFollowButtonClick = onFollowButtonClick
                    ) {
                        onBoardingFinish()
                    }

                    Text(
                        text = stringResource(id = R.string.trending_posts_title),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(bottom = LargeSpacing),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(items = postsUiState.post, key = { post -> post.id }) {
                PostListItem(
                    post = it,
                    onPostClick = onPostClick,
                    onProfileClick = onProfileClick,
                    onLikeClick = onLikeClick,
                    onCommentClick = onCommentClick
                )
            }
        }
        LaunchedEffect(shouldFetchMorePosts) {
            if (shouldFetchMorePosts) {
                fetchMoreData() // Llama a la función para cargar más datos
            }
        }




//            if (postsFeedUiState.isLoading && postsFeedUiState.posts.isNotEmpty()) {
//                item(key = Constants.LOADING_MORE_ITEM_KEY) {
//                    Box(
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(vertical = MediumSpacing, horizontal = LargeSpacing),
//                        contentAlignment = Alignment.Center
//                    ) {
//                        CircularProgressIndicator()
//                    }
//                }
//            }
//        }
//
//        PullRefreshIndicator(
//            refreshing = isRefreshing,
//            onRefresh = {
//                isRefreshing = true
//                onUiAction(HomeUiAction.RefreshAction)
//            },
//            modifier = modifier.align(Alignment.TopCenter)
//        )
//
//        LaunchedEffect(key1 = shouldFetchMorePosts) {
//            if (shouldFetchMorePosts && !postsFeedUiState.endReached) {
//                onUiAction(HomeUiAction.LoadMorePostsAction)
//            }
//        }
//
//        LaunchedEffect(key1 = homeRefreshState.isRefreshing) {
//            isRefreshing = homeRefreshState.isRefreshing
//            launch {
//                delay(1000) // Simulate some refresh delay
//                isRefreshing = false
//            }
//        }
    }
}
