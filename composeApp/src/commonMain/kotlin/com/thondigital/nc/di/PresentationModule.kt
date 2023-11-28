package com.thondigital.nc.di

import com.thondigital.nc.domain.usecase.auth.signin.email.SignInUseCase
import com.thondigital.nc.domain.usecase.auth.status.AuthenticationStatusUseCase
import com.thondigital.nc.presentation.ui.auth.signin.SignInScreenModel
import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.event.EventDetailsScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule =
    module {
        // screen models
        factory {
            HomeScreenModel(
                AuthenticationStatusUseCase(get()),
            )
        }
        factory { CalendarScreenModel() }
        factory { EventDetailsScreenModel() }
        factory { SignInScreenModel(SignInUseCase(get(), get(named("ioDispatcher")))) }
    }
