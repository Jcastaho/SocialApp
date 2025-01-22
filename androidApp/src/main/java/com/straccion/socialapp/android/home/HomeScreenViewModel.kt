package com.straccion.socialapp.android.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.straccion.socialapp.android.coomon.fake_data.Post
import com.straccion.socialapp.android.coomon.fake_data.samplePosts
import com.straccion.socialapp.android.coomon.fake_data.sampleUsers
import com.straccion.socialapp.android.home.onboarding.OnBoardingUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {
    var postsUiState by mutableStateOf(PostsUiState())
        private set

    var onBoardingUiState by mutableStateOf(OnBoardingUiState())

    init {
        fetchData()
    }

    fun fetchData(){
        onBoardingUiState = onBoardingUiState.copy(isLoading = true)
        postsUiState = postsUiState.copy(isLoading = true)
        viewModelScope.launch {
            delay(1000)

            onBoardingUiState = onBoardingUiState.copy(
                isLoading = false,
                users = sampleUsers,
                shouldShowOnBoarding = true
            )

            postsUiState = postsUiState.copy(
                isLoading = false,
                post = samplePosts
            )
        }
    }
}

data class PostsUiState(
    val isLoading: Boolean = false,
    val post: List<Post> = listOf(),
    val errorMessage: String? = null
)