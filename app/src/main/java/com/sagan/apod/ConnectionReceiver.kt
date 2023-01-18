package com.sagan.apod

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import com.sagan.apod.ui.view.activities.OffLineScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ConnectionReceiver @Inject constructor(): BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        val hasConnection = networkInfo != null && networkInfo.isConnected
        if(!hasConnection) {
            Toast.makeText(context, "No tienes conexión. Puede que la app no funcione correctamente", Toast.LENGTH_LONG).show()
                val newIntent = Intent(context, OffLineScreenActivity::class.java)
                context.startActivity(newIntent)
       }
    }
}