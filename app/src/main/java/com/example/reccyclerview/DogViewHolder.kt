package com.example.reccyclerview

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.reccyclerview.databinding.ItemDogsBinding
import com.squareup.picasso.Picasso

class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    //private val binding = ItemDogsBinding.bind(view)
    private val binding: ImageView = view.findViewById(R.id.ivDog)
    fun bind(image:String){
        Picasso.get().load(image).into(binding)
    }
}