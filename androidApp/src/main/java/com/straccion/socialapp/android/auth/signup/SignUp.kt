package com.straccion.socialapp.android.auth.signup

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.straccion.socialapp.android.auth.destinations.LoginDestination
import org.koin.androidx.compose.koinViewModel

@Destination(start = true)
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
            navigator.navigate(LoginDestination)
        }
    )


}