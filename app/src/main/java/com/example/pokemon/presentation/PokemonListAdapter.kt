package com.example.pokemon.presentation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.data.network.responses.Result
import com.example.pokemon.databinding.ItemBinding
import com.example.pokemon.util.Constants.PIKACHU
import com.example.pokemon.util.capitalizeFirstLetter

class PokemonListAdapter(
    private val listener: PokemonAdapterListener
) : PagingDataAdapter<Result, PokemonListAdapter.AdapterViewHolder>(COMPARATOR) {

    class AdapterViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemBinding.bind(item)
        private val context = item.context
        fun bind(result: Result?, listener: PokemonAdapterListener, position: Int) {
            with(binding) {
                tvPokemonName.text = result?.name?.capitalizeFirstLetter() ?: PIKACHU
                itemView.setOnClickListener {
                    if (result != null) {
                        listener.onClickPokemon(result)
                    }
                }
                tvPokemonId.text = context.getString(R.string.pokemon_id, position + 1)
                Glide.with(itemView)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${position + 1}.png")
                    .into(ivPokemonImage)
            }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        holder.bind(getItem(position), listener, position = position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return AdapterViewHolder(view)
    }
}
