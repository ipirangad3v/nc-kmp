package com.thondigital.nc.network.connectivity

import Kmule.networkStatus
import com.thondigital.nc.data.connectivity.ConnectivityChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultConnectivityChecker : ConnectivityChecker {
    private var isNetworkAvailable = false
    private val scope = CoroutineScope(Dispatchers.Main)

    init {
        scope.launch {
            networkStatus.collect {
                isNetworkAvailable = it
            }
        }
    }


    override fun isNetworkAvailable(): Boolean = isNetworkAvailable
}
