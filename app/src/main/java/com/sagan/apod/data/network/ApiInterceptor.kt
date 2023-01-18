package com.sagan.apod.data.network

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiInterceptor @Inject constructor() : Interceptor, AppCompatActivity() {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            400 -> { Log.i("ApiInterceptorError 400","Error 400")
                       }
            401 -> {
                Log.i("ApiInterceptorError 401","Error 401")
            }

            403 -> {
                Log.i("ApiInterceptorError 403","Error 403")
            }

            404 -> {
                Log.i("ApiInterceptorError 404","Error 404")
            }
            200 -> {
                Log.i("ApiInterceptorError 200","Response 200")
             }
    }
        return response
}
}