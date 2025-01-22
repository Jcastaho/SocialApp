package com.straccion.socialapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.destinations.HomeDestination
import com.straccion.socialapp.android.destinations.LoginDestination
import com.straccion.socialapp.android.destinations.SignUpDestination
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun SignUp(
    navigator: DestinationsNavigator
) {
    val viewModel: SignUpViewModel = koinViewModel()
    SignUpScreen(
        uiState = viewModel.uiState,
        onUsernameChanged = viewModel::updateUsername,
        onEmailChanged = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onNavigateLogin = {
            navigator.navigate(LoginDestination){
                popUpTo(SignUpDestination) { inclusive = true }
            }
        },
        onNavigateToHome = {
            navigator.navigate(HomeDestination){
                popUpTo(SignUpDestination) { inclusive = true }
            }
        },
        onSignUpClick = viewModel::signUp,
        onNavigateToLogin = {
            navigator.navigate(LoginDestination){
                popUpTo(SignUpDestination){
                    inclusive = true
                }
            }
        }
    )
}