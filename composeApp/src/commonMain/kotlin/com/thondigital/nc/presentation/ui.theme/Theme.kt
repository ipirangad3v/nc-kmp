package com.thondigital.nc.presentation.ui.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors

val DarkColorScheme = darkColors(
    primary = BluePrimaryDark,
    secondary = GreenSecondaryDark,
    onPrimary = OnGreenDark,
    onSecondary = OnGreenSecondaryDark,
    background = BackgroundDark,
    onBackground = OnBackgroundDark,
    surface = SurfaceDark,
    onSurface = OnSurfaceDark,
    error = ErrorDark,
    onError = OnErrorDark,
)

val LightColorScheme = lightColors(
    primary = primaryBlue,
    secondary = GreenSecondaryLight,
    onPrimary = OnGreenLight,
    onSecondary = OnGreenSecondaryLight,
    background = BackgroundLight,
    onBackground = OnBackgroundLight,
    surface = SurfaceLight,
    onSurface = OnSurfaceLight,
    error = ErrorLight,
    onError = OnErrorLight,
)