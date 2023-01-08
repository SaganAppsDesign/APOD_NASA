package com.example.apod.ui.view.activities

import Constants.YOUTUBE_API_KEY
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apod.databinding.ActivityDetailBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso

class DetailActivity : YouTubeBaseActivity(){
    private lateinit var binding : ActivityDetailBinding
    private lateinit var image : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initYouTubePlayer()

        val intent = intent
        val title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")!!.toString()
        val description = intent.getStringExtra("description")
        val mediaType = intent.getStringExtra("mediaType")

        if(mediaType=="image"){
            binding.tvTitle.text = title
            binding.tvDescription.text = description
            Picasso.get().load(image).into(binding.ivImage)
            binding.YouTubeView.visibility = View.GONE
        } else {
            binding.YouTubeView.visibility = View.VISIBLE
        }
    }

    private fun initYouTubePlayer(){
        binding.YouTubeView.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                if(!p2){
                    val a = image.substring(30, 41)
                    player?.loadVideo(a)
                    player?.play()
                    println("Esto es una prueba ${image.substring(30)}")
                }
           }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.i("p1", p1.toString())
            }
        })
    }
}