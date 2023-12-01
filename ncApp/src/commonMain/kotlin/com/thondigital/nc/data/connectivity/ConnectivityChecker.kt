package com.thondigital.nc.data.connectivity

interface ConnectivityChecker {
    fun isNetworkAvailable(): Boolean
}
