package com.straccion.socialapp.android.coomon.components


import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FollowsButton(
    modifier: Modifier = Modifier,
    @StringRes text: Int,
    onClick: () -> Unit,
    isOutline: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = if (isOutline) {
            ButtonDefaults.outlinedButtonColors()
        } else {
            ButtonDefaults.buttonColors()
        },
        border = if (isOutline) {
            ButtonDefaults.outlinedButtonBorder
        } else {
            null
        },
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp
        )

    ) {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            )
        )
    }

}