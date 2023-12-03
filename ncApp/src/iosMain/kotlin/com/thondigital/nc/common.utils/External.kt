package com.thondigital.nc.common.utils

import com.thondigital.nc.network.model.NetworkConstants
import com.thondigital.nc.network.model.NetworkConstants.YOUTUBE_CHANNEL_URL
import platform.Foundation.NSURL
import platform.UIKit.UIApplication

actual fun openSpotify() {
    val spotifyUrl = NSURL(string = "spotify:show:${NetworkConstants.SPOTIFY_SHOW_ID}")

    val application = UIApplication.sharedApplication
    if (application.canOpenURL(spotifyUrl)) {
        application.openURL(spotifyUrl)
    } else {
        // Se o Spotify não estiver instalado, abra a página web do show
        val webUrl = NSURL(string = YOUTUBE_CHANNEL_URL)
        application.openURL(webUrl)
    }
}

actual fun openYouTubeChannel() {
    val youtubeUrl = NSURL(string = YOUTUBE_CHANNEL_URL)

    val application = UIApplication.sharedApplication
    if (application.canOpenURL(youtubeUrl)) {
        application.openURL(youtubeUrl)
    } else {
        // Abre a URL no navegador Safari se o aplicativo do YouTube não estiver disponível
        application.openURL(youtubeUrl)
    }
}

actual fun openInstagramProfile() {
    val instagramUrl = NSURL(string = NetworkConstants.INSTAGRAM_PROFILE_URL)
    val application = UIApplication.sharedApplication
    if (application.canOpenURL(instagramUrl)) {
        application.openURL(instagramUrl)
    } else {
        // Abre a URL no navegador Safari se o aplicativo do Instagram não estiver disponível
        application.openURL(instagramUrl)
    }
}
