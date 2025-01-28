package com.straccion.socialapp.android.coomon.components

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.theming.DarkGray
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.coomon.theming.LightGray
import com.straccion.socialapp.android.coomon.theming.MediumSpacing
import com.straccion.socialapp.post.domain.model.PostComment

@Composable
fun CommentListItem(
    modifier: Modifier = Modifier,
    comment: PostComment,
    onProfileClick: (Long) -> Unit,
    onMoreIconClick: (PostComment) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(LargeSpacing),
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        CircleImage(
            imageUrl = comment.userImageUrl,
            modifier = modifier.size(30.dp),
            onClick = { onProfileClick(comment.userId) }
        )
        Column(
            modifier = modifier.weight(1f)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Text(
                    text = comment.userName,
                    style = MaterialTheme.typography.displaySmall,
                    modifier = modifier.alignByBaseline()
                )
                Text(
                    text = comment.createdAt,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSystemInDarkTheme()) {
                        DarkGray
                    } else {
                        LightGray
                    },
                    modifier = modifier
                        .alignByBaseline()
                        .weight(1f)
                )
                Icon(
                    painter = painterResource(R.drawable.round_more_horiz_24),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) {
                        DarkGray
                    } else {
                        LightGray
                    },
                    modifier = modifier.clickable { onMoreIconClick(comment) }
                )
            }

            Text(text = comment.content, style = MaterialTheme.typography.displaySmall)
        }
    }
}
//
//@Composable
//fun CommentItemLayout(
//    modifier: Modifier,
//    image: @Composable () -> Unit,
//    username: @Composable () -> Unit,
//    date: @Composable () -> Unit,
//    moreIcon: @Composable () -> Unit,
//    commentText: @Composable () -> Unit
//) {
//    Layout(
//        contents = listOf(
//            image,
//            username,
//            date,
//            moreIcon,
//            commentText
//        ),
//        modifier = modifier
//            .padding(
//                top = LargeSpacing,
//                bottom = LargeSpacing,
//                start = LargeSpacing,
//                end = MediumSpacing
//            )
//    ) { (imageMeasurables,
//            usernameMeasurables,
//            dateMeasurables,
//            moreIconMeasurables,
//            commentTextMeasurables),
//        constraints ->
//
//        val imagePlaceable = imageMeasurables.first().measure(constraints)
//        val usernamePlaceable = usernameMeasurables.first().measure(constraints)
//        val datePlaceable = dateMeasurables.first().measure(constraints)
//        val moreIconPlaceable = moreIconMeasurables.first().measure(constraints)
//
//        val commentTextConstraints = constraints.copy(
//            maxWidth = constraints.maxWidth - (24.dp.roundToPx() + imagePlaceable.width)
//        )
//        val commentTextPlaceable = commentTextMeasurables.first().measure(commentTextConstraints)
//
//        val totalHeight = maxOf(
//            imagePlaceable.height,
//            (usernamePlaceable.height + commentTextPlaceable.height)
//        )
//
//        layout(width = constraints.maxWidth, height = totalHeight) {
//            imagePlaceable.placeRelative(x = 0, y = 0)
//            usernamePlaceable.placeRelative(
//                x = imagePlaceable.width + (8.dp.roundToPx()),
//                y = 0
//            )
//            datePlaceable.placeRelative(
//                x = imagePlaceable.width + usernamePlaceable.width + (16.dp.roundToPx()),
//                y = 3.dp.roundToPx()
//            )
//
//            moreIconPlaceable.placeRelative(
//                x = (constraints.maxWidth) - (moreIconPlaceable.width),
//                y = 0
//            )
//
//            commentTextPlaceable.place(
//                x = imagePlaceable.width + (8.dp.roundToPx()),
//                y = usernamePlaceable.height + (2.dp.roundToPx())
//            )
//        }
//    }
//}