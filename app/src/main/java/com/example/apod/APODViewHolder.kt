package com.example.apod

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class APODViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: ImageView = view.findViewById(R.id.ivApod)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    fun bind(image: String?, title: String?){
        Picasso.get().load(image).into(binding)
        tvTitle.text = title
     }
}