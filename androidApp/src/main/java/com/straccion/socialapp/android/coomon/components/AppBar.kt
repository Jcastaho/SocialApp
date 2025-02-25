package com.straccion.socialapp.android.coomon.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.straccion.socialapp.android.MainActivityUiState
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.destinations.LoginDestination
import com.straccion.socialapp.android.destinations.SignUpDestination
import com.straccion.socialapp.android.coomon.theming.SmallElevation
import com.straccion.socialapp.android.destinations.CreatePostDestination
import com.straccion.socialapp.android.destinations.EditProfileDestination
import com.straccion.socialapp.android.destinations.FollowersDestination
import com.straccion.socialapp.android.destinations.FollowingDestination
import com.straccion.socialapp.android.destinations.HomeDestination
import com.straccion.socialapp.android.destinations.PostDetailDestination
import com.straccion.socialapp.android.destinations.ProfileDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    val currentDestination = navHostController.currentDestinationAsState().value
    Surface(
        modifier = modifier,
        tonalElevation = SmallElevation
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(getAppBartitle(currentDestination?.route)))
            },
            modifier = modifier.background(MaterialTheme.colorScheme.surface),
            actions = {
                AnimatedVisibility(visible = currentDestination?.route == HomeDestination.route) {
                    IconButton(onClick = {

                    }) {

                    }
                }
            },
            navigationIcon = {
                if (shouldShowNavigationIcon(currentDestination?.route)) {
                    IconButton(onClick = {navHostController.navigateUp()}) {
                        Icon(
                            painter = painterResource(R.drawable.round_arrow_back),
                            contentDescription = null
                        )
                    }
                } else {
                    null
                }
            }
        )
    }
}

private fun getAppBartitle(currentDestinationRoute: String?): Int {
    return when (currentDestinationRoute) {
        LoginDestination.route -> R.string.login_destination_title
        SignUpDestination.route -> R.string.signup_destination_title
        HomeDestination.route -> R.string.home_destination_title
        PostDetailDestination.route -> R.string.post_detail_destination_title
        ProfileDestination.route -> R.string.profile_destination_title
        EditProfileDestination.route -> R.string.edit_profile_destination_title
        FollowingDestination.route -> R.string.following_text
        FollowersDestination.route -> R.string.followers_text
        CreatePostDestination.route -> R.string.posts_label
        else -> R.string.no_destination_title
    }
}

private fun shouldShowNavigationIcon(currentDestinationRoute: String?): Boolean {
    return !(
            currentDestinationRoute == LoginDestination.route
                    || currentDestinationRoute == SignUpDestination.route
                    || currentDestinationRoute == HomeDestination.route
                    || currentDestinationRoute == null
            )
}