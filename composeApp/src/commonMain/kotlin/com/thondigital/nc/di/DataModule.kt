package com.thondigital.nc.di

import com.thondigital.nc.data.mapper.AccountDataDomainMapper
import com.thondigital.nc.data.mapper.TokensDataDomainMapper
import com.thondigital.nc.data.repository.account.DefaultAccountRepository
import com.thondigital.nc.data.repository.auth.DefaultAuthRepository
import com.thondigital.nc.domain.repository.account.AccountRepository
import com.thondigital.nc.domain.repository.auth.AuthRepository
import org.koin.dsl.module

val dataModule =
    module {
        single<AuthRepository> {
            DefaultAuthRepository(
                get(),
                get(),
                TokensDataDomainMapper(),
            )
        }
        single<AccountRepository> {
            DefaultAccountRepository(
                get(),
                get(),
                AccountDataDomainMapper(),
            )
        }
    }
