package com.thondigital.nc.network.utils

fun Boolean.toLong(): Long = if (this) 1 else 0

fun Number?.toBoolean() = this?.toInt() == 1
