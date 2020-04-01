@file:Suppress("DEPRECATION")

package com.meksconway.areyouexpert.util

import android.content.Context
import android.net.ConnectivityManager

fun isNetworkAvaible(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager){
        val networkInfo = connectivityManager.activeNetworkInfo
        return true == networkInfo?.isConnected
    } else false
}
fun getSystemService(service: String): Any {
    TODO("Not yet implemented")
}



