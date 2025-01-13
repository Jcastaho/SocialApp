package com.straccion.socialapp.android.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.straccion.socialapp.android.auth.signup.SignUpUiState

class LoginViewModel: ViewModel() {
    var uiState by mutableStateOf(SignUpUiState())
        private set
    fun updateEmail(input: String){
        uiState = uiState.copy(email = input)
    }
    fun updatePassword(input: String){
        uiState = uiState.copy(password = input)
    }
}

data class LoginUiState(
    var email: String = "",
    var password: String = ""
)