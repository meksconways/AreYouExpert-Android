@file:Suppress("DEPRECATION", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "USELESS_IS_CHECK"
)

package com.meksconway.areyouexpert.util

import android.content.Context
import android.net.ConnectivityManager

fun isNetworkAvaible(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return if (connectivityManager is ConnectivityManager){
        val networkInfo = connectivityManager.activeNetworkInfo
        networkInfo.isConnected
    } else false
}
fun getSystemService(service: String): Any {
    TODO("Not yet implemented")
}



