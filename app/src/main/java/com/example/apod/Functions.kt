package com.example.apod

import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.databinding.ActivityMainBinding

object Functions: AppCompatActivity() {

    fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
    fun showError2() {
        Toast.makeText(this, "Introduce una cantidad menor a 50, por favor", Toast.LENGTH_SHORT).show()
    }

}