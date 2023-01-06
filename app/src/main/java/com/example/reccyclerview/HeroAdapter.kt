package com.example.reccyclerview

import android.icu.number.NumberFormatter.with
import android.icu.number.NumberRangeFormatter.with
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class HeroAdapter(val superhero:List<SuperHero>):RecyclerView.Adapter<HeroAdapter.HeroHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HeroHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun onBindViewHolder(holder: HeroHolder, position: Int) {
        holder.render(superhero[position])
    }

    override fun getItemCount(): Int = superhero.size


    class HeroHolder(val view:View):RecyclerView.ViewHolder(view){

        fun render(superhero: SuperHero){

            val tvRealName: TextView = view.findViewById(R.id.tvRealName)
            tvRealName.text = superhero.heroName

            val tvHeroName: TextView = view.findViewById(R.id.tvHeroName)
            tvHeroName.text = superhero.realName

            val tvPublisher: TextView = view.findViewById(R.id.tvPublisher)
            tvPublisher.text = superhero.publisher

            view.setOnClickListener{Toast.makeText(view.context, "${superhero.heroName}", Toast.LENGTH_SHORT).show()}

            val ivHero: ImageView = view.findViewById(R.id.ivHero)
           // load the image with Picasso
            Picasso.get().load(superhero.image).into(ivHero)
        }
    }











}