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

@Composable
fun Menu() {
    val navigator = LocalNavigator.currentOrThrow
    LazyVerticalGrid(
        modifier =
            Modifier.fillMaxWidth().height(
                400.dp
            ),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        columns = GridCells.Adaptive(minSize = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        items(getNavigationItems()) {
            MenuItem(it) {
                navigator.push(it.screen)
            }
        }
    }
}
