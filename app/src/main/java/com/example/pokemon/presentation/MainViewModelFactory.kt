package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.util.Constants.UNCHECKED

class MainViewModelFactory(
    val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {

    @Suppress(UNCHECKED)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(pokemonRepository) as T
    }
}
