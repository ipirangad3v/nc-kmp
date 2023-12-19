package com.thondigital.nc.presentation.navigation

import Kmule.openInstagram
import Kmule.openSpotify
import Kmule.openYouTube
import com.thondigital.nc.domain.model.ClickableMenuItem
import com.thondigital.nc.network.model.NetworkConstants
import com.thondigital.nc.presentation.ui.account.AccountScreen
import com.thondigital.nc.presentation.ui.calendar.CalendarScreen
import com.thondigital.nc.presentation.ui.external.ExternalScreen

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
                    openSpotify(NetworkConstants.SPOTIFY_SHOW_ID)
                },
                resourceId = "podcast.png",
                name = "Podcast"
            ),
            ClickableMenuItem(
                screen =
                ExternalScreen {
                    openYouTube(
                        NetworkConstants.YOUTUBE_CHANNEL_ID
                    )
                },
                resourceId = "youtube.png",
                name = "Youtube"
            ),
            ClickableMenuItem(
                screen =
                ExternalScreen {
                    openInstagram(
                        NetworkConstants.INSTAGRAM_PROFILE_ID
                    )
                },
                resourceId = "instagram.png",
                name = "Instagram"
            ),
            ClickableMenuItem(
                screen = AccountScreen,
                resourceId = "account.png",
                name = "Conta"
            ),
        )
}
