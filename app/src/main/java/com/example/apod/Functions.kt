package com.example.apod

import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.databinding.ActivityMainBinding

object Functions: AppCompatActivity() {

//    fun hideKeyboard(binding: ActivityMainBinding) {
//        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.hideSoftInputFromWindow(binding.viewRoot.windowToken, 0)
//    }

    fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }
}