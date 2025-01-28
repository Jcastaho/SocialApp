package com.straccion.socialapp.android

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.currentDestinationAsState
import com.straccion.socialapp.android.coomon.components.AppBar
import com.straccion.socialapp.android.destinations.HomeDestination
import com.straccion.socialapp.android.destinations.LoginDestination


@Composable
fun SocialApp(
    uiState: MainActivityUiState
) {
    val navHostController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val systemUiController = rememberSystemUiController()

    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark){
        MaterialTheme.colorScheme.surface
    }else{
        MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
    }
    SideEffect {
        systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isSystemInDark
        )
    }
    val currentRoute = navHostController.currentDestinationAsState().value?.route
    val isvisible = currentRoute == HomeDestination.route

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            AppBar(modifier = Modifier, navHostController = navHostController)
        },

        floatingActionButton = {
            if (isvisible){
                FloatingActionButton(
                    onClick = {
                        navHostController.navigate(
                            route = "create_post"
                        )
                    },
                    shape = CircleShape, // Hace el botón circular
                    modifier = Modifier
                        .size(56.dp), // Tamaño del botón
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "Create post",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    ) {innerPaddings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerPaddings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    when(uiState){
        MainActivityUiState.Loading -> {}
        is MainActivityUiState.Success -> {
            LaunchedEffect(key1 = Unit) {
                if (uiState.currentUser.token.isEmpty()) {
                    navHostController.navigate(LoginDestination.route) {
                        popUpTo(HomeDestination.route) {
                            inclusive = true
                        }
                    }
                }
            }
        }
    }
}