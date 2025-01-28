package com.straccion.socialapp.android.coomon.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.straccion.socialapp.android.coomon.theming.LargeSpacing
import com.straccion.socialapp.android.coomon.theming.MediumSpacing
import com.straccion.socialapp.android.coomon.util.Constants.LOADING_MORE_ITEM_KEY

internal fun LazyListScope.loadingMoreItem() {
    item(key = LOADING_MORE_ITEM_KEY) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = MediumSpacing,
                    horizontal = LargeSpacing
                ),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}