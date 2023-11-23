package com.thondigital.nc.data.remote

import com.thondigital.nc.domain.models.EventModel
import com.thondigital.nc.domain.models.HomeResponse
import com.thondigital.nc.domain.repository.HomeRepository

class FakeHomeRepository : HomeRepository {
    override suspend fun getHome(): HomeResponse =
        HomeResponse(
            listOf(
                EventModel(
                    1,
                    "Congresso de Jovens",
                    "22/11",
                ),
                EventModel(
                    1,
                    "Congresso de mulheres",
                    "34/11",
                ),
            ),
        )
}
