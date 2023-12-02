package com.thondigital.nc.network.model.response

import kotlinx.serialization.Serializable

@Serializable
open class GenericResponse(
    open val status: String = "",
    open val message: String = ""
)
