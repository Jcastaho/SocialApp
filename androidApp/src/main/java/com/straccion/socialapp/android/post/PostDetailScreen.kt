package com.straccion.socialapp.android.post

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.components.CommentListItem
import com.straccion.socialapp.android.coomon.components.PostListItem
import com.straccion.socialapp.android.coomon.fake_data.Comment
import com.straccion.socialapp.android.coomon.fake_data.sampleComments
import com.straccion.socialapp.android.coomon.theming.LargeSpacing

@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostUiState,
    commentsUiState: CommentsUiState,
    onCommentMoreIconClick: (Comment) -> Unit,
    onProfileClick: (Int) -> Unit,
    onAddCommentClick: () -> Unit,
    fetchData: () -> Unit

) {
    if (postUiState.isLoading && commentsUiState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()

        }
    } else if (postUiState.post != null) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            item(key = "post_item") {
                PostListItem(
                    post = postUiState.post,
                    onPostClick = {},
                    onProfileClick = onProfileClick,
                    onLikeClick = {},
                    onCommentClick = {},
                    isDetailScreen = true
                )
            }

            item(key = "comments_header_section") {
                CommentsSectionHeader {
                    onAddCommentClick()
                }
            }

            items(items = sampleComments, key = { comment -> comment.id }) {
                Divider()
                CommentListItem(
                    comment = it,
                    onProfileClick = onProfileClick
                ) {
                    onCommentMoreIconClick(it)
                }
            }
        }
    } else {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LargeSpacing)
            ) {
                Text(
                    text = stringResource(R.string.loading_error_message),
                    style = MaterialTheme.typography.labelLarge
                )
                OutlinedButton(
                        onClick = fetchData
                ) {
                    Text(
                        text = stringResource(R.string.retry_button_text)
                    )
                }
            }
        }
    }
    LaunchedEffect(key1 = Unit, block = {
        fetchData()
    })
}

@Composable
fun CommentsSectionHeader(
    modifier: Modifier = Modifier,
    onAddCommentClick: () -> Unit
) {

    Row(
        modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.comments_label),
            style = MaterialTheme.typography.displayMedium
        )
        OutlinedButton(onClick = onAddCommentClick) {
            Text(text = stringResource(R.string.comment_hint))
        }
    }

}