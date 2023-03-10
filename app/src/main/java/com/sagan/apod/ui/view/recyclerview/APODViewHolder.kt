package com.sagan.apod.ui.view.recyclerview

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sagan.apod.R
import com.sagan.apod.ui.view.activities.DetailActivity
import com.squareup.picasso.Picasso

class APODViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val ivAPOD: ImageView = view.findViewById(R.id.ivApod)
    private val tvTitle: TextView = view.findViewById(R.id.tvTitle)
    private val tvDate: TextView = view.findViewById(R.id.tvDate)
    private val tvMediaType: TextView = view.findViewById(R.id.tvMediaType)
    private val share: ImageButton = view.findViewById(R.id.bnShare)

    fun bind(
        image: String?,
        title: String?,
        description: String?,
        date: String?,
        mediaType: String?,
        thumbnail: String?,
        context: Context){

        if (mediaType == "video"){
            Picasso.get().load(thumbnail).into(ivAPOD)
            ivAPOD.scaleType = ImageView.ScaleType.CENTER
           }
        else Picasso.get().load(image).into(ivAPOD)

        tvTitle.text = title
        tvDate.text = date
        tvMediaType.text = mediaType

        share.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, image)
            context.startActivity(Intent.createChooser(shareIntent, "Share APOD"))
        }

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