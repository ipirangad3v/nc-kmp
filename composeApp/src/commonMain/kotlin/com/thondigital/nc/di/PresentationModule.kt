package com.thondigital.nc.di

import com.thondigital.nc.domain.usecase.auth.signin.email.SignInUseCase
import com.thondigital.nc.presentation.ui.auth.signin.SignInScreenModel
import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.event.EventDetailsScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule =
    module {
        // screen models
        factory { HomeScreenModel() }
        factory { CalendarScreenModel() }
        factory { EventDetailsScreenModel() }
        factory { SignInScreenModel(SignInUseCase(get(), get(named("ioDispatcher")))) }
    }
