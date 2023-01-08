package com.example.apod.data.network

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.Constants.APOD_API_KEY
import com.example.apod.Functions.showError
import com.example.apod.core.RetrofitHelper
import com.example.apod.data.model.APODResponse
import com.example.apod.databinding.ActivityMainBinding
import com.example.apod.ui.view.recyclerview.APODAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object GetApodByCount: AppCompatActivity() {

    @SuppressLint("NotifyDataSetChanged")
    fun searchByCount(
        query: String,
        adapter: APODAdapter,
    ): MutableList<APODResponse?>{

        val apodData = mutableListOf<APODResponse?>()
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitHelper.getRetrofit().create(APIService::class.java).getAPODByCount("apod?api_key=$APOD_API_KEY&count=$query")
            val apod = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    apodData.clear()
                    apodData.addAll(apod!!)
                    adapter.notifyDataSetChanged()

                } else showError()
            }
        }
        return apodData
    }
}