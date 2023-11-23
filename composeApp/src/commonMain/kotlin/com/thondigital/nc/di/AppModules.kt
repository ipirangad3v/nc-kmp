package com.thondigital.nc.di

import com.thondigital.nc.data.remote.FireStoreRepositoryImpl
import com.thondigital.nc.domain.repository.FirestoreRepository
import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.event.EventDetailsScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import org.koin.dsl.module

val appModule =
    module {
        // repositories
        single<FirestoreRepository> { FireStoreRepositoryImpl() }


        // screen models
        factory { HomeScreenModel(get()) }
        factory { CalendarScreenModel() }
        factory { EventDetailsScreenModel(get()) }
    }
