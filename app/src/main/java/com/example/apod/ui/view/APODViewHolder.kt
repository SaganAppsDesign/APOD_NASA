package com.example.apod.ui.view

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.R
import com.example.apod.ui.view.activities.DetailActivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class APODViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val ivAPOD: ImageView = view.findViewById(R.id.ivApod)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val tvDate: TextView = view.findViewById(R.id.tvDate)
    private val tvMediaType: TextView = view.findViewById(R.id.tvMediaType)

    fun bind(image: String?,
             title: String?,
             description: String?,
             date: String?,
             mediaType: String?,
             context: Context){

        Picasso.get().load(image).into(ivAPOD)
        tvTitle.text = title
        tvDate.text = date
        tvMediaType.text = mediaType

        itemView.setOnClickListener {
            passData(tvTitle.text.toString(), image, description, mediaType, context)
        }
     }

    private fun passData(title: String, image: String?, description: String?, mediaType: String?, context: Context){

        val intent = Intent(context, DetailActivity::class.java)

        intent.putExtra("title", title)
        intent.putExtra("image", image)
        intent.putExtra("description", description)
        intent.putExtra("mediaType", mediaType)
        context.startActivity(intent)
    }
}