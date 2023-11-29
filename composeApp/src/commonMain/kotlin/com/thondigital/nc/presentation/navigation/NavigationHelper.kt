package com.thondigital.nc.presentation.navigation

import com.thondigital.nc.domain.model.ClickableMenuItem
import com.thondigital.nc.presentation.ui.calendar.CalendarScreen
import com.thondigital.nc.presentation.ui.streaming.RadioStreamingScreen

object NavigationHelper {
    fun getNavigationItems(): List<ClickableMenuItem> =
        listOf(
            ClickableMenuItem(
                screen = CalendarScreen,
                resourceId = "calendar.png",
                name = "Calendário",
            ),
            ClickableMenuItem(
                screen = RadioStreamingScreen,
                resourceId = "radio.png",
                name = "Rádio Boas Novas",
            ),
            ClickableMenuItem(
                screen = CalendarScreen,
                resourceId = "podcast.png",
                name = "Podcast",
            ),
            ClickableMenuItem(
                screen = CalendarScreen,
                resourceId = "article.png",
                name = "Artigos",
            ),
        )
}
