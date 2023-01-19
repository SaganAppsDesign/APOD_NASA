package com.sagan.apod.ui.view.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sagan.apod.R

class APODAdapter(private val context: Context,
                  private val images: MutableList<String?>,
                  private val titles: MutableList<String?>,
                  private val description: MutableList<String?>,
                  private val date: MutableList<String?>,
                  private val mediaType: MutableList<String?>,
                  private val thumbnail: MutableList<String?>)
    : RecyclerView.Adapter<APODViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): APODViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return APODViewHolder(layoutInflater.inflate(R.layout.item_apod, parent, false))
    }

    override fun onBindViewHolder(holder: APODViewHolder, position: Int) {
        val image = images[position]
        val title = titles[position]
        val description = description[position]
        val date = date[position]
        val mediaType = mediaType[position]
        val thumbnail = thumbnail[position]
        if(mediaType != "other"){
            holder.bind(image, title, description, date, mediaType, thumbnail, context)
        }
      }

    override fun getItemCount(): Int = images.size
}