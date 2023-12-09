package com.thondigital.nc.di

import com.thondigital.nc.domain.usecase.account.delete.DeleteAccountUseCase
import com.thondigital.nc.domain.usecase.account.detail.GetAccountUseCase
import com.thondigital.nc.domain.usecase.account.detail.SyncAccountUseCase
import com.thondigital.nc.domain.usecase.auth.signin.email.SignInUseCase
import com.thondigital.nc.domain.usecase.auth.signup.SignUpUseCase
import com.thondigital.nc.domain.usecase.auth.status.AuthenticationStatusUseCase
import com.thondigital.nc.presentation.ui.account.AccountScreenModel
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
                AuthenticationStatusUseCase(get()),
                GetAccountUseCase(get()),
                SyncAccountUseCase(get(), get(named("ioDispatcher")))
            )
        }
        factory { CalendarScreenModel() }
        factory { EventDetailsScreenModel() }
        factory { SignInScreenModel(SignInUseCase(get(), get(named("ioDispatcher")))) }
        factory {
            RadioStreamingScreenModel()
        }
        factory {
            SignUpScreenModel(
                SignUpUseCase(get(), get(named("ioDispatcher")))
            )
        }
        factory {
            AccountScreenModel(
                AuthenticationStatusUseCase(get()),
                GetAccountUseCase(get()),
                DeleteAccountUseCase(get()),
                SyncAccountUseCase(get(), get(named("ioDispatcher")))
            )
        }
    }
