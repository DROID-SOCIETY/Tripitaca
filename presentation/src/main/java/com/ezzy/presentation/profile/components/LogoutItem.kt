package com.ezzy.presentation.profile.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ezzy.designsystem.utils.DpDimensions

@Composable
fun LogoutItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    onItemClick: () -> Unit
) {

    Surface(
        onClick = { onItemClick() },
        shape = RoundedCornerShape(DpDimensions.Small),
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = DpDimensions.Small,
                vertical = DpDimensions.Small
            )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = Color.Red
            )

            Text(
                text = title, style = MaterialTheme.typography.titleMedium,
                color = Color.Red,
                modifier = Modifier
                    .padding(horizontal = DpDimensions.Normal)
                    .weight(1f)
            )
        }

    }

}