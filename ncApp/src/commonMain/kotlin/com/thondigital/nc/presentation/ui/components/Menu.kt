package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.presentation.navigation.NavigationHelper.getNavigationItems
import kotlin.math.ceil

@Composable
fun Menu() {
    val items = getNavigationItems()
    val menuItemHeight = 100.dp
    val columns = 3
    val sizeMultiplier = 1.25f
    val navigator = LocalNavigator.currentOrThrow

    val rows = ceil(items.size / columns.toFloat()).toInt()

    val totalHeight = menuItemHeight * rows

    val adjustedHeight = totalHeight * sizeMultiplier + 16.dp

    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth().height(adjustedHeight),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(items) {
            MenuItem(it) {
                navigator.push(it.screen)
            }
        }
    }
}
