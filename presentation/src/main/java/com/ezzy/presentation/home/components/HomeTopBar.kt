package com.ezzy.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ezzy.data.domain.model.UserData
import com.ezzy.designsystem.utils.DpDimensions
import com.ezzy.presentation.R
import com.google.firebase.auth.FirebaseUser

@Composable
fun HomeTopBar(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    userData: FirebaseUser?
) {

    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DpDimensions.Small, vertical = DpDimensions.Normal),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(userData?.photoUrl)
                    .placeholder(R.drawable.placeholder)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(DpDimensions.Dp50)
                    .clip(CircleShape),
            )

            Spacer(modifier = Modifier.width(DpDimensions.Small))

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(DpDimensions.Smallest)
            ) {
                Text(
                    text = "Good Morning 👋",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inversePrimary
                )

                Text(
                    text = userData?.displayName.toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.inversePrimary
                )
            }

            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .testTag("notification")
                    .border(width = 1.dp, color = MaterialTheme.colorScheme.outline, CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notification icon",
                    tint = MaterialTheme.colorScheme.inversePrimary
                )
            }

        }
    }

}