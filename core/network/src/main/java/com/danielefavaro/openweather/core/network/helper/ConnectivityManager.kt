package com.danielefavaro.openweather.core.network.helper

import android.content.Context
import android.net.ConnectivityManager

class ConnectivityManager(private val context: Context) {

    fun doWeHaveInternet(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo?.let {
            return it.isConnectedOrConnecting
        } ?: false
    }
}
