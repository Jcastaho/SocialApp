package com.straccion.socialapp.android.auth.login

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.destinations.HomeDestination
import com.straccion.socialapp.android.destinations.LoginDestination
import com.straccion.socialapp.android.destinations.SignUpDestination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun Login(
    navigator: DestinationsNavigator
) {
    val viewModel: LoginViewModel = koinViewModel()
    LoginScreen(
        uiState = viewModel.uiState,
        onEmailChanged = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onSignInClick = viewModel::signIn,
        onNavigateToHome = {
            navigator.navigate(HomeDestination){
                popUpTo(LoginDestination) { inclusive = true }
            }
        },
        onNavigateToSignup = {
            navigator.navigate(SignUpDestination){
                popUpTo(LoginDestination) { inclusive = true }
            }
        }
    )
}