package com.straccion.socialapp.android.coomon.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.fake_data.Post
import com.straccion.socialapp.android.coomon.theming.DarkGray
import com.straccion.socialapp.android.coomon.theming.ExtraLargeSpacing
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.coomon.theming.LightGray
import com.straccion.socialapp.android.coomon.theming.MediumSpacing


@Composable
fun PostListItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Post) -> Unit,
    onProfileClick: (Int) -> Unit,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    isDetailScreen: Boolean = false
) {
    val isSystemInDark = isSystemInDarkTheme()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable { onPostClick(post) }
            .padding(bottom = ExtraLargeSpacing)
    ) {
        PostItemHeader(
            name = post.authorName,
            profileUrl = post.authorImage,
            date = post.createdAt,
            onProfileClick = { onProfileClick(post.authorId) }
        )
        AsyncImage(
            model = post.imageUrl,
            contentDescription = null,
            modifier.fillMaxWidth()
                .aspectRatio(ratio = 1.0f)
            ,
            placeholder = if (isSystemInDark) {
                painterResource(R.drawable.dark_image_place_holder)
            } else {
                painterResource(R.drawable.light_image_place_holder)
            },
            contentScale = ContentScale.Crop
        )
        PostLikesRow(
            likesCount = post.likesCount,
            commentsCount = post.commentCount,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick
        )
        Text(
            text = post.text,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.padding(horizontal = LargeSpacing),
            maxLines = if (isDetailScreen){
                20
            }else{
                2
            },
            overflow = TextOverflow.Ellipsis
        )
    }

}

@Composable
fun PostItemHeader(
    modifier: Modifier = Modifier,
    name: String,
    profileUrl: String,
    date: String,
    onProfileClick: () -> Unit
) {
    val isSystemInDark = isSystemInDarkTheme()
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = LargeSpacing, vertical = MediumSpacing),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
            imageUrl = profileUrl,
            modifier = modifier.size(30.dp)
        ) {
            onProfileClick()
        }
        Text(
            text = name,
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = modifier
                .size(4.dp)
                .clip(CircleShape)
                .background(
                    if (isSystemInDark) {
                        DarkGray
                    } else {
                        LightGray
                    }
                )
        )
        Text(
            text = date, style = MaterialTheme.typography.labelLarge.copy(
                textAlign = TextAlign.Start,
                fontSize = 12.sp,
                color = if (isSystemInDark) {
                    DarkGray
                } else {
                    LightGray
                }
            ),
            modifier = modifier.weight(1f)
        )
        Icon(
            painter = painterResource(R.drawable.round_more_horizontal),
            contentDescription = null,
            tint = if (isSystemInDark) {
                DarkGray
            } else {
                LightGray
            }
        )
    }
}

@Composable
fun PostLikesRow(
    modifier: Modifier = Modifier,
    likesCount: Int,
    commentsCount: Int,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit
) {
    Row(
        modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp, horizontal = MediumSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val isSystemInDark = isSystemInDarkTheme()
        IconButton(onClick = onLikeClick) {
            Icon(
                painter = painterResource(R.drawable.like_icon_outlined),
                contentDescription = null,
                tint = if (isSystemInDark) {
                    DarkGray
                } else {
                    LightGray
                }
            )
        }
        Text(
            text = "$likesCount",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 18.sp
            )
        )
        Spacer(modifier.width(MediumSpacing))

        IconButton(onClick = onCommentClick) {
            Icon(
                painter = painterResource(R.drawable.chat_icon_outlined),
                contentDescription = null,
                tint = if (isSystemInDark) {
                    DarkGray
                } else {
                    LightGray
                }
            )
        }
        Text(
            text = "$commentsCount",
            style = MaterialTheme.typography.displaySmall.copy(
                fontSize = 18.sp
            )
        )
    }
}