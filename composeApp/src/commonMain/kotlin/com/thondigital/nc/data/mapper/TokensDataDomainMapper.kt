package com.thondigital.nc.data.mapper

import com.thondigital.nc.common.mapper.Mapper
import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.domain.model.TokensDomainModel

/**
 * Mapper class for convert [TokensDataModel] to [TokensDomainModel] and vice versa
 */
class TokensDataDomainMapper() : Mapper<TokensDataModel, TokensDomainModel> {
    override fun from(i: TokensDataModel): TokensDomainModel {
        return TokensDomainModel(
            accessToken = i.accessToken,
            refreshToken = i.refreshToken,
        )
    }

    override fun to(o: TokensDomainModel): TokensDataModel {
        return TokensDataModel(
            accessToken = o.accessToken,
            refreshToken = o.refreshToken,
        )
    }
}
