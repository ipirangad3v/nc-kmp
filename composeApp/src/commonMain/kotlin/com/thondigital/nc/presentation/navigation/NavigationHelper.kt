package com.thondigital.nc.presentation.navigation

import com.thondigital.nc.domain.models.ClickableMenuItem
import com.thondigital.nc.presentation.ui.calendar.CalendarScreen

object NavigationHelper {
    fun getNavigationItems(): List<ClickableMenuItem> = listOf(
        ClickableMenuItem(
            screen = CalendarScreen,
            resourceId = "calendar.png",
            name = "Calendário",
        ),
        ClickableMenuItem(
            screen = CalendarScreen,
            resourceId = "podcast.png",
            name = "Podcast",
        ),
    )

}