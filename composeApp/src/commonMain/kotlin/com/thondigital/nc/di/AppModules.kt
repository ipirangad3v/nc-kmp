package com.thondigital.nc.di

import com.thondigital.nc.data.remote.FakeEventRepository
import com.thondigital.nc.data.remote.FakeHomeRepository
import com.thondigital.nc.domain.repository.EventRepository
import com.thondigital.nc.domain.repository.HomeRepository
import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.event.EventDetailsScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import org.koin.dsl.module

val appModule = module {
    //repositories
    single<HomeRepository> { FakeHomeRepository() }
    single<EventRepository> { FakeEventRepository() }

    //screen models
    factory { HomeScreenModel(get()) }
    factory { CalendarScreenModel() }
    factory { EventDetailsScreenModel(get()) }
}