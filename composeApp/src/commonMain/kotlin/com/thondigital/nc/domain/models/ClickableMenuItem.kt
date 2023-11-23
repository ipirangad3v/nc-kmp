package com.thondigital.nc.domain.models

import cafe.adriel.voyager.core.screen.Screen

data class ClickableMenuItem(
    val screen: Screen,
    val resourceId: String,
    val name: String,
)
