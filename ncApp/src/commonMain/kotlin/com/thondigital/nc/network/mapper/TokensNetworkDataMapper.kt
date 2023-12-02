package com.thondigital.nc.network.mapper

import com.thondigital.nc.common.mapper.Mapper
import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.network.model.response.TokensNetworkModel

/**
 * Mapper class for convert [TokensNetworkModel] to [TokensDataModel] and vice versa
 */
class TokensNetworkDataMapper : Mapper<TokensNetworkModel, TokensDataModel> {
    override fun from(i: TokensNetworkModel): TokensDataModel {
        return TokensDataModel(
            accessToken = i.accessToken.orEmpty(),
            refreshToken = i.refreshToken.orEmpty(),
            status = i.status,
            message = i.message
        )
    }

    override fun to(o: TokensDataModel): TokensNetworkModel {
        return TokensNetworkModel(
            accessToken = o.accessToken,
            refreshToken = o.refreshToken,
            status = o.status,
            message = o.message
        )
    }
}
