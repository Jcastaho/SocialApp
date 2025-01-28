package com.straccion.socialapp.android.post

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.components.CircleImage
import com.straccion.socialapp.android.coomon.components.CommentListItem
import com.straccion.socialapp.android.coomon.components.PostListItem
import com.straccion.socialapp.android.coomon.components.ScreenLevelLoadingErrorView
import com.straccion.socialapp.android.coomon.components.ScreenLevelLoadingView
import com.straccion.socialapp.android.coomon.components.loadingMoreItem
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.coomon.theming.MediumSpacing
import com.straccion.socialapp.android.coomon.theming.SmallSpacing
import com.straccion.socialapp.android.coomon.util.Constants.COMMENTS_HEADER_KEY
import com.straccion.socialapp.android.coomon.util.Constants.POST_ITEM_KEY
import com.straccion.socialapp.post.domain.model.PostComment
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PostDetailScreen(
    modifier: Modifier = Modifier,
    postUiState: PostUiState,
    commentsUiState: CommentsUiState,
    postId: Long,
    onProfileNavigation: (userId: Long) -> Unit,
    onUiAction: (PostDetailUiAction) -> Unit
) {
    val listState = rememberLazyListState()

    val shouldFetchMoreComments by remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val visibleItemsInfo = layoutInfo.visibleItemsInfo
            if (layoutInfo.totalItemsCount == 0) {
                false
            } else {
                val lastVisibleItem = visibleItemsInfo.last()
                (lastVisibleItem.index + 1 == layoutInfo.totalItemsCount)
            }
        }
    }

    var commentText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    val scope = rememberCoroutineScope()
    var selectedComment by rememberSaveable(stateSaver = postCommentSaver) {
        mutableStateOf(null)
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            selectedComment?.let { postComment ->
                CommentMoreActionsBottomSheetContent(
                    comment = postComment,
                    canDeleteComment =
                    postComment.userId == postUiState.post?.userId || postComment.isOwner,
                    onDeleteCommentClick = { comment ->
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onUiAction(PostDetailUiAction.RemoveCommentAction(comment))
                                selectedComment = null
                            }
                        }
                    },
                    onNavigateToProfile = { userId ->
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                selectedComment = null
                                onProfileNavigation(userId)
                            }
                        }
                    }
                )
            }
        }
    ) {
        if (postUiState.isLoading) {
            ScreenLevelLoadingView()
        } else if (postUiState.post != null) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface)
                        .weight(1f),
                    state = listState
                ) {
                    item(key = POST_ITEM_KEY) {
                        PostListItem(
                            post = postUiState.post,
                            onProfileClick = {},
                            onLikeClick = { onUiAction(PostDetailUiAction.LikeOrDislikePostAction(it)) },
                            onCommentClick = {}
                        )
                    }

                    item(key = COMMENTS_HEADER_KEY) {
                        CommentsHeaderSection() {}
                    }

                    if (commentsUiState.isAddingNewComment) {
                        loadingMoreItem()
                    }

                    items(
                        items = commentsUiState.comments,
                        key = { comment -> comment.commentId }
                    ) { postComment ->
                        CommentListItem(
                            comment = postComment,
                            onProfileClick = {},
                            onMoreIconClick = {
                                selectedComment = it
                                scope.launch { sheetState.show() }
                            }
                        )
                    }

                    if (commentsUiState.isLoading) {
                        loadingMoreItem()
                    }
                }

                CommentInput(
                    commentText = commentText,
                    onCommentChange = {
                        commentText = it
                    },
                    onSendClick = {
                        keyboardController?.hide()
                        onUiAction(PostDetailUiAction.AddCommentAction(it))
                        commentText = ""
                    }
                )

            }
        } else {
            ScreenLevelLoadingErrorView {
                onUiAction(PostDetailUiAction.FetchPostAction(postId))
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        onUiAction(PostDetailUiAction.FetchPostAction(postId))
    }

    LaunchedEffect(key1 = shouldFetchMoreComments) {
        if (shouldFetchMoreComments && !commentsUiState.endReached) {
            onUiAction(PostDetailUiAction.LoadMoreCommentsAction)
        }
    }
}


@Composable
private fun CommentInput(
    modifier: Modifier = Modifier,
    commentText: String,
    onCommentChange: (String) -> Unit,
    onSendClick: (String) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .animateContentSize()
    ) {
        Divider()

        Row(
            modifier = modifier.padding(
                horizontal = LargeSpacing,
                vertical = MediumSpacing
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            Box(
                modifier = modifier
                    .heightIn(min = 35.dp, max = 70.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(percent = 25)
                    )
                    .padding(
                        horizontal = MediumSpacing,
                        vertical = SmallSpacing
                    )
                    .weight(1f)
            ) {
                BasicTextField(
                    value = commentText,
                    onValueChange = onCommentChange,
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterStart),
                    textStyle = LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onSurface),
                    cursorBrush = SolidColor(LocalContentColor.current)
                )

                if (commentText.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = SmallSpacing),
                        text = stringResource(id = R.string.comment_hint),
                        style =  MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    )
                }
            }

            SendCommentButton(
                sendCommentEnabled = commentText.isNotBlank(),
                onSendClick = {
                    onSendClick(commentText)
                }
            )
        }


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CommentMoreActionsBottomSheetContent(
    modifier: Modifier = Modifier,
    comment: PostComment,
    canDeleteComment: Boolean,
    onDeleteCommentClick: (comment: PostComment) -> Unit,
    onNavigateToProfile: (userId: Long) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.comment_more_actions_title),
            style = MaterialTheme.typography.displaySmall,
            modifier = modifier.padding(all = LargeSpacing)
        )

        Divider()

        ListItem(
            modifier = modifier.clickable(
                enabled = canDeleteComment,
                onClick = {
                    onDeleteCommentClick(comment)
                }
            ),
            text = {
                Text(
                    text = stringResource(id = R.string.delete_comment_action),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            icon = {
                Icon(Icons.Default.Delete, contentDescription = null)
            }
        )

        ListItem(
            modifier = modifier.clickable {
                onNavigateToProfile(comment.userId)
            },
            text = {
                Text(
                    text = stringResource(id = R.string.view_profile_action, comment.userName),
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            icon = {
                CircleImage(
                    imageUrl = comment.userImageUrl,
                    modifier = modifier
                        .size(25.dp),
                    onClick = {}
                )
            }
        )

    }
}


@Composable
private fun SendCommentButton(
    modifier: Modifier = Modifier,
    sendCommentEnabled: Boolean,
    onSendClick: () -> Unit
) {
    Button(
        modifier = modifier,
        enabled = sendCommentEnabled,
        colors = ButtonDefaults.buttonColors(),
        border = ButtonDefaults.outlinedButtonBorder,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        ),
        onClick = onSendClick,
    ) {
        Text(
            stringResource(id = R.string.send_button_text),
            modifier = modifier.padding(horizontal = 5.dp)
        )
    }

}

private val postCommentSaver = Saver<PostComment?, Any>(
    save = { postComment ->
        if (postComment != null) {
            mapOf(
                "commentId" to postComment.commentId,
                "content" to postComment.content,
                "postId" to postComment.postId,
                "userId" to postComment.userId,
                "userName" to postComment.userName,
                "userImageUrl" to postComment.userImageUrl,
                "createdAt" to postComment.createdAt
            )
        } else {
            null
        }
    },
    restore = { savedValue ->
        val map = savedValue as Map<*, *>
        PostComment(
            commentId = map["commentId"] as Long,
            content = map["content"] as String,
            postId = map["postId"] as Long,
            userId = map["userId"] as Long,
            userName = map["userName"] as String,
            userImageUrl = map["userImageUrl"] as String?,
            createdAt = map["createdAt"] as String
        )
    }
)

@Composable
fun CommentsHeaderSection(
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