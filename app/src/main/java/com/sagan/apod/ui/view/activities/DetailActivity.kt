package com.sagan.apod.ui.view.activities

import com.sagan.apod.Constants.YOUTUBE_API_KEY
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.sagan.apod.R
import com.sagan.apod.databinding.ActivityDetailBinding
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
        initViews()
    }

    private fun initYouTubePlayer(){
        binding.YouTubeView.initialize(YOUTUBE_API_KEY, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                   val a = image.substring(30, 41)
                   player?.loadVideo(a)
                   player?.play()
           }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.i("p1", p1.toString())
                Log.i("p0", p0.toString())
            }
        })
    }

    private fun initViews(){
        val intent = intent
        val title = intent.getStringExtra("title")
        image = intent.getStringExtra("image")!!.toString()
        val description = intent.getStringExtra("description")
        val mediaType = intent.getStringExtra("mediaType")

        binding.tvTitle.text = title
        binding.tvDescription.text = description
        binding.tvTitle.typeface = ResourcesCompat.getFont(this, R.font.monserrat_alternates)
        binding.tvDescription.typeface = ResourcesCompat.getFont(this, R.font.monserrat_alternates)

        if(mediaType=="image"){
            Picasso.get().load(image).into(binding.ivImage)
            binding.YouTubeView.visibility = View.GONE
        } else {
            initYouTubePlayer()
            binding.YouTubeView.visibility = View.VISIBLE
            binding.ivImage.visibility = View.GONE
        }
    }
}