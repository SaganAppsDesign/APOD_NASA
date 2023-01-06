package com.example.apod.ui.view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.R
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class APODViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding: ImageView = view.findViewById(R.id.ivApod)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    fun bind(image: String?, title: String?){
        Picasso.get().load(image).into(binding)
        tvTitle.text = title
     }
    init {
        itemView.setOnClickListener { v: View  ->
           Snackbar.make(v, "Título ${tvTitle.text}", Snackbar.LENGTH_LONG).setAction("Action", null).show()
//            passData(title.text.toString(), description, date.text.toString(), copyright.text.toString(), imageString, mediaType)
        }
    }



}