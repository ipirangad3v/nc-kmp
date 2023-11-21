package com.thondigital.nc.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel

class HomeScreenModel : ScreenModel {

    var name by mutableStateOf("Home")
        private set

}