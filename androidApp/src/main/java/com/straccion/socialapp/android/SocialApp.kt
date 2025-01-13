package com.straccion.socialapp.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.straccion.socialapp.android.auth.NavGraphs

@Composable
fun SocialApp() {
    val navHostController = rememberNavController()

    DestinationsNavHost(navGraph = NavGraphs.root, navController = navHostController)
}