package com.straccion.socialapp.android.coomon.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.fake_data.Comment
import com.straccion.socialapp.android.coomon.fake_data.sampleComments
import com.straccion.socialapp.android.coomon.theming.DarkGray
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.coomon.theming.LightGray
import com.straccion.socialapp.android.coomon.theming.MediumSpacing
import com.straccion.socialapp.android.coomon.theming.SocialAppTheme

@Composable
fun CommentListItem(
    modifier: Modifier = Modifier,
    comment: Comment,
    onProfileClick: (Int) -> Unit,
    onMoreIconClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(

            imageUrl = comment.authorImageUrl,
            modifier = modifier.size(30.dp)
        ) {
            onProfileClick(comment.authorId)
        }
        Column(
            modifier = modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Text(
                    text = comment.authorName,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = modifier.alignByBaseline()
                )
                Text(
                    text = comment.date,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSystemInDarkTheme()) {
                        DarkGray
                    } else {
                       LightGray
                    },
                    modifier = modifier.alignByBaseline().weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) {
                        DarkGray
                    } else {
                        LightGray
                    },
                    modifier = modifier.clickable { onMoreIconClick() }
                )
            }

            Text(text = comment.comment, style = MaterialTheme.typography.displaySmall)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CommentListItemPreview() {
    SocialAppTheme {
        Surface (color = MaterialTheme.colorScheme.surface) {
            CommentListItem(
                comment = sampleComments.first(),
                onProfileClick = {},
                onMoreIconClick = {}
            )
        }
    }
}