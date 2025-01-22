package com.straccion.socialapp.android.home.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.straccion.socialapp.android.R
import com.straccion.socialapp.android.coomon.components.CircleImage
import com.straccion.socialapp.android.coomon.components.FollowsButton
import com.straccion.socialapp.android.coomon.components.PostItemHeader
import com.straccion.socialapp.android.coomon.components.PostListItem
import com.straccion.socialapp.android.coomon.fake_data.FollowsUser
import com.straccion.socialapp.android.coomon.theming.MediumSpacing
import com.straccion.socialapp.android.coomon.theming.SmallSpacing

@Composable
fun OnBoardingUserItem(
    modifier: Modifier = Modifier,
    followsUser: FollowsUser,
    onUserClick: (FollowsUser) -> Unit,
    isFollowing: Boolean = false,
    onFollowingButtonClick: (Boolean, FollowsUser) -> Unit
) {
    Card(
        modifier = modifier
            .height(140.dp)
            .width(130.dp)
            .shadow(0.dp)
            .clickable { onUserClick(followsUser) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface // Adapta el color según el tema
        )

    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(MediumSpacing),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircleImage(
                modifier = modifier.size(50.dp),
                imageUrl = followsUser.profileUrl
            ) {
                onUserClick(followsUser)
            }
            Spacer(modifier.height(SmallSpacing))
            Text(
                text = followsUser.name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier.height(MediumSpacing))

            FollowsButton(
                modifier = modifier
                    .fillMaxWidth()
                    .heightIn(30.dp),
                text = R.string.follow_text_label,
                onClick = { onFollowingButtonClick(!isFollowing, followsUser) }
            )
        }
    }
}