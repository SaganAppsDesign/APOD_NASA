package com.sagan.apod

import androidx.appcompat.app.AppCompatActivity

object Functions : AppCompatActivity(){

     fun getAppVersion(): String {
        return BuildConfig.VERSION_NAME
    }
}