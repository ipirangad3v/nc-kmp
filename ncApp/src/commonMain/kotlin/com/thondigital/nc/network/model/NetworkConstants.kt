package com.thondigital.nc.network.model

object NetworkConstants {
    const val YOUTUBE_CHANNEL_URL = "https://m.youtube.com/@nacaodacruzchurch1612"
    const val INSTAGRAM_PROFILE_URL = "https://www.instagram.com/nacaodacruz.church/"
    const val BASE_ENDPOINT = "https://nc-server-332d35e07665.herokuapp.com/"
    const val SIGNIN_ENDPOINT = "auth/signin"
    const val SIGNUP_ENDPOINT = "auth/signup"
    const val REFRESH_TOKEN_ENDPOINT = "auth/token/refresh"
    const val REVOKE_TOKEN_ENDPOINT = "auth/token/revoke"
    const val ACCOUNT_ENDPOINT = "auth/account"
    const val PASSWORD_ENDPOINT = "auth/account/password"
    const val RBN_STREAMING_ENDPOINT =
        "https://euroticast5.euroti.com.br/stream.php?porta=8006&1701226225506"
    const val SPOTIFY_SHOW_ID = "3EL7nYdrA4Y43U16fRzQfv"
}

object NetworkParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
}
