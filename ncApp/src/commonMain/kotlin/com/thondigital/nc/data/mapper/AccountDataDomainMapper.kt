package com.thondigital.nc.data.mapper

import com.thondigital.nc.common.mapper.Mapper
import com.thondigital.nc.data.model.AccountDataModel
import com.thondigital.nc.domain.model.AccountDomainModel

/**
 * Mapper class for convert [AccountDataModel] to [AccountDomainModel] and vice versa
 */
class AccountDataDomainMapper() :
    Mapper<AccountDataModel, AccountDomainModel> {
    override fun from(i: AccountDataModel): AccountDomainModel {
        return AccountDomainModel(
            pk = i.pk,
            email = i.email,
            username = i.username,
            isAdmin = i.isAdmin
        )
    }

    override fun to(o: AccountDomainModel): AccountDataModel {
        return AccountDataModel(
            pk = o.pk,
            email = o.email,
            username = o.username,
            isAdmin = o.isAdmin
        )
    }
}
