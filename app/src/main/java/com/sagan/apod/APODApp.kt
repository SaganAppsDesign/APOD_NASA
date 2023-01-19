package com.sagan.apod

import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import com.sagan.apod.data.network.APODService
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class APODApp: Application(){

    @Inject
    lateinit var br: ConnectionReceiver

    override fun onCreate() {
        super.onCreate()
        activeReceiver()
    }

    private fun activeReceiver(){
        val networkIntentFilter = IntentFilter()
        networkIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(br, networkIntentFilter)
    }
}