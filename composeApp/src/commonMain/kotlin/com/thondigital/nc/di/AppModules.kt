package com.thondigital.nc.di

import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import org.koin.dsl.module

val appModule = module {
    factory { HomeScreenModel() }
    factory { CalendarScreenModel() }
}