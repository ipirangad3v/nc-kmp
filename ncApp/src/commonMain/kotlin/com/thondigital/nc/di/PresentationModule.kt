package com.thondigital.nc.di

import com.thondigital.nc.domain.usecase.auth.signin.email.SignInUseCase
import com.thondigital.nc.domain.usecase.auth.signup.SignUpUseCase
import com.thondigital.nc.domain.usecase.auth.status.AuthenticationStatusUseCase
import com.thondigital.nc.domain.usecase.streaming.radiostream.StartRadioStreamUseCase
import com.thondigital.nc.domain.usecase.streaming.radiostream.StopRadioStreamUseCase
import com.thondigital.nc.presentation.ui.auth.signin.SignInScreenModel
import com.thondigital.nc.presentation.ui.auth.signup.SignUpScreenModel
import com.thondigital.nc.presentation.ui.calendar.CalendarScreenModel
import com.thondigital.nc.presentation.ui.event.EventDetailsScreenModel
import com.thondigital.nc.presentation.ui.home.HomeScreenModel
import com.thondigital.nc.presentation.ui.streaming.RadioStreamingScreenModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule =
    module {
        // screen models
        factory {
            HomeScreenModel(
                AuthenticationStatusUseCase(get())
            )
        }
        factory { CalendarScreenModel() }
        factory { EventDetailsScreenModel() }
        factory { SignInScreenModel(SignInUseCase(get(), get(named("ioDispatcher")))) }
        factory {
            RadioStreamingScreenModel(
                StartRadioStreamUseCase(get()),
                StopRadioStreamUseCase(get())
            )
        }
        factory {
            SignUpScreenModel(
                SignUpUseCase(get(), get(named("ioDispatcher")))
            )
        }
    }
