package com.thondigital.nc.network.model

object NetworkConstants {
    const val BASE_ENDPOINT = "https://nc-server-332d35e07665.herokuapp.com/"
    const val SIGNIN_ENDPOINT = "auth/signin"
    const val SIGNUP_ENDPOINT = "auth/signup"
    const val REFRESH_TOKEN_ENDPOINT = "auth/token/refresh"
    const val REVOKE_TOKEN_ENDPOINT = "auth/token/revoke"
    const val ACCOUNT_ENDPOINT = "auth/account"
    const val PASSWORD_ENDPOINT = "auth/account/password"
}

object NetworkParameters {
    const val TOKEN_TYPE = "Bearer "
    const val AUTH_HEADER = "Authorization"
    const val CUSTOM_HEADER = "@"
    const val NO_AUTH = "NoAuth"
    const val SEARCH_QUERY = "search_query"
}
