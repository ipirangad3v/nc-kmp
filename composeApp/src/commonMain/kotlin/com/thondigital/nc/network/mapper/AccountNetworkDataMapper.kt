package com.thondigital.nc.network.mapper

import com.thondigital.nc.common.exception.MappingException
import com.thondigital.nc.common.mapper.Mapper
import com.thondigital.nc.data.model.AccountDataModel
import com.thondigital.nc.network.model.response.AccountNetworkModel

/**
 * Mapper class for convert [AccountNetworkModel] to [AccountDataModel] and vice versa
 */
class AccountNetworkDataMapper : Mapper<AccountNetworkModel, AccountDataModel> {
    override fun from(i: AccountNetworkModel): AccountDataModel {
        return AccountDataModel(
            pk = i.id ?: throw MappingException("Account pk cannot be null"),
            email = i.email.orEmpty(),
            username = i.username.orEmpty(),
        )
    }

    override fun to(o: AccountDataModel): AccountNetworkModel {
        return AccountNetworkModel(
            id = o.pk,
            email = o.email,
            username = o.username,
        )
    }
}
