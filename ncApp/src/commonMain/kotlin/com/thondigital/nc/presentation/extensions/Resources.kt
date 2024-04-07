package com.thondigital.nc.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
@OptIn(ExperimentalResourceApi::class)
fun resourceFromShowPassword(showPassword: MutableState<Boolean>) =
    painterResource(DrawableResource(if (showPassword.value) "images/eye.png" else "images/eyeoff.png"))

@Composable
@OptIn(ExperimentalResourceApi::class)
fun painterResourceFromPath(path: String) = painterResource(DrawableResource(path))
