package com.thondigital.nc.network.connectivity

import com.thondigital.nc.data.connectivity.ConnectivityChecker

class DefaultConnectivityChecker : ConnectivityChecker {
    override fun isNetworkAvailable(): Boolean {
//        ConnectivityStatus()
        return true
    }
}
