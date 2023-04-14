package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.data.network.PokemonRepository

class MainViewModelFactory(
    val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(
            pokemonRepository,
        ) as T
    }

}