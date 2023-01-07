package com.example.apod.ui.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.databinding.ActivityDetailBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        val title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        Picasso.get().load(image).into(binding.ivImage)

    }
}