package com.thondigital.nc.common.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.thondigital.nc.NCApp
import com.thondigital.nc.network.model.NetworkConstants
import com.thondigital.nc.network.model.NetworkConstants.YOUTUBE_CHANNEL_URL

actual fun openSpotify() {
    val context: Context = NCApp.appContext
    val intent =
        Intent(Intent.ACTION_VIEW, Uri.parse("spotify:show:${NetworkConstants.SPOTIFY_SHOW_ID}")).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

    if (context.packageManager.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    } else {
        // Se o Spotify não estiver instalado, abra a página web do show
        val webIntent =
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://open.spotify.com/show/${NetworkConstants.SPOTIFY_SHOW_ID}")
            ).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        context.startActivity(webIntent)
    }
}

actual fun openYouTubeChannel() {
    val context: Context = NCApp.appContext
    val youtubeUrl = YOUTUBE_CHANNEL_URL
    val intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

    if (context.packageManager.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    } else {
        // Abre a URL no navegador se nenhum aplicativo correspondente for encontrado
        val webIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        context.startActivity(webIntent)
    }
}

actual fun openInstagramProfile() {
    val context: Context = NCApp.appContext
    val instagramUrl = NetworkConstants.INSTAGRAM_PROFILE_URL
    val intent =
        Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

    if (context.packageManager.resolveActivity(intent, 0) != null) {
        context.startActivity(intent)
    } else {
        // Abre a URL no navegador se nenhum aplicativo correspondente for encontrado
        val webIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        context.startActivity(webIntent)
    }
}
