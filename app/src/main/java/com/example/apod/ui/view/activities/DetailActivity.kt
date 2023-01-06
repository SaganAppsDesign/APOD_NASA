package com.example.apod.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.apod.databinding.ActivityDetailBinding
import com.example.apod.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}