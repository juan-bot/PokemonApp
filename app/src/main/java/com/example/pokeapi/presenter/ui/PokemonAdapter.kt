package com.example.pokeapi.presenter.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokeapi.R
import com.example.pokeapi.domain.model.PokeResponse
import com.example.pokeapi.domain.model.Pokemon

class PokemonAdapter(
    private val dataSet: List<Pokemon>,
    private val onItemClick: (Pokemon) -> Unit
) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.poke_item_row, viewGroup, false)

        return ViewHolder(view)
    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtName: TextView = view.findViewById(R.id.itemName)
        val txtDescription: TextView = view.findViewById(R.id.itemDescription)
        val txtWei: TextView = view.findViewById(R.id.itemWeight)
        val imgPoke: ImageView = view.findViewById(R.id.itemImage)


    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if(dataSet.isNotEmpty()){
            val pokemon = dataSet[position]
            viewHolder.txtName.text = dataSet[position].name
            viewHolder.txtDescription.text = "Base Experience:" + dataSet[position].base_experience.toString()
            viewHolder.txtWei.text = dataSet[position].weight.toString()
            Glide.with(viewHolder.itemView.context)
                .load(pokemon.sprites?.back_default ?: "https://images.app.goo.gl/73WRS1pZMUsUk2ru9")
                .into(viewHolder.imgPoke)
            //Glide.with(viewHolder.itemView.context).load(dataSet[position].sprites.back_default).into(viewHolder.imgPoke)
            viewHolder.itemView.setOnClickListener{
                onItemClick(pokemon)
            }
        }

    }

    override fun getItemCount() = dataSet.size
}