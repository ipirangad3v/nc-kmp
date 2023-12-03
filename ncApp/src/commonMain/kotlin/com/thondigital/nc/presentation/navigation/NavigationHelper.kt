package com.thondigital.nc.presentation.navigation

import com.thondigital.nc.common.utils.openInstagramProfile
import com.thondigital.nc.common.utils.openSpotify
import com.thondigital.nc.common.utils.openYouTubeChannel
import com.thondigital.nc.domain.model.ClickableMenuItem
import com.thondigital.nc.presentation.ui.calendar.CalendarScreen
import com.thondigital.nc.presentation.ui.podcast.ExternalScreen

object NavigationHelper {
    fun getNavigationItems(): List<ClickableMenuItem> =
        listOf(
            ClickableMenuItem(
                screen = CalendarScreen,
                resourceId = "calendar.png",
                name = "Calendário"
            ),
            ClickableMenuItem(
                screen =
                    ExternalScreen {
                        openSpotify()
                    },
                resourceId = "podcast.png",
                name = "Podcast"
            ),
            ClickableMenuItem(
                screen =
                    ExternalScreen {
                        openYouTubeChannel()
                    },
                resourceId = "youtube.png",
                name = "Youtube"
            ),
            ClickableMenuItem(
                screen =
                    ExternalScreen {
                        openInstagramProfile()
                    },
                resourceId = "instagram.png",
                name = "Instagram"
            )
        )
}
