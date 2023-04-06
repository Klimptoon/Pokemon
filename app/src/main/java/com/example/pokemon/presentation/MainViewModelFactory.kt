package com.example.pokemon.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.domain.GetPokemonUseCase

class MainViewModelFactory(
    val getPokemonUseCase: GetPokemonUseCase,
    val pokemonRepository: PokemonRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainFragmentViewModel(
            pokemonRepository,
            getPokemonUseCase = getPokemonUseCase
        ) as T
    }

}