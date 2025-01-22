package com.straccion.socialapp.android

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.straccion.socialapp.android.coomon.components.AppBar
import com.straccion.socialapp.android.destinations.HomeDestination
import com.straccion.socialapp.android.destinations.LoginDestination


@Composable
fun SocialApp(
    token: String?
) {
    val navHostController = rememberNavController()
    val systemUiController = rememberSystemUiController()
    val snackbarHostState = remember { SnackbarHostState() }


    val isSystemInDark = isSystemInDarkTheme()
    val statusBarColor = if (isSystemInDark) {
        MaterialTheme.colorScheme.surface
    } else {
        MaterialTheme.colorScheme.surface.copy(alpha = 0.95f)
    }
    SideEffect {
        systemUiController.setSystemBarsColor(color = statusBarColor, darkIcons = !isSystemInDark)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            AppBar(navHostController = navHostController)

        }
    ) { innerpadings ->
        DestinationsNavHost(
            modifier = Modifier.padding(innerpadings),
            navGraph = NavGraphs.root,
            navController = navHostController
        )
    }

    LaunchedEffect(key1 = token, block = {
        if (token != null && token.isEmpty()) {
            navHostController.navigate(LoginDestination.route) {
                popUpTo(HomeDestination.route) { inclusive = true }
            }
        }
    })

}