package com.ezzy.presentation.listing_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PagerIndicator(currentPage: Int, items: List<String>,
                   modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(top = 20.dp)
    ) {
        repeat(items.size) {
            Indicator(isSelected = it == currentPage)
        }
    }
}