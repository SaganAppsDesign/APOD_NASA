package com.example.apod.ui.view
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.R

class APODAdapter(private val images: MutableList<String?>, private val titles: MutableList<String?>)
    : RecyclerView.Adapter<APODViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): APODViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return APODViewHolder(layoutInflater.inflate(R.layout.item_apod, parent, false))
    }

    override fun onBindViewHolder(holder: APODViewHolder, position: Int) {
        val item = images[position]
        val title = titles[position]
        holder.bind(item, title)
    }

    override fun getItemCount(): Int = images.size

}