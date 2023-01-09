package com.example.apod.domain

import androidx.appcompat.app.AppCompatActivity
import com.example.apod.data.APODRepository
import com.example.apod.data.model.APODResponse

class GetApodByCountUseCase: AppCompatActivity() {
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun searchByCount(
//        query: String,
//        adapter: APODAdapter
//    ): MutableList<APODResponse?>{
//
//        val apodData = mutableListOf<APODResponse?>()
//        CoroutineScope(Dispatchers.IO).launch {
//            val call = RetrofitHelper.getRetrofit().create(APIService::class.java).getAPODByCount("apod?api_key=$APOD_API_KEY&count=$query")
//            val apod = call.body()
//            runOnUiThread {
//                if (call.isSuccessful) {
//                    apodData.clear()
//                    apodData.addAll(apod!!)
//                    adapter.notifyDataSetChanged()
//
//                } else showError()
//            }
//        }
//        return apodData
//    }

    private val repository = APODRepository()

    suspend operator fun invoke (query: String):List<APODResponse?> = repository.getAPODsByCount(query)

}