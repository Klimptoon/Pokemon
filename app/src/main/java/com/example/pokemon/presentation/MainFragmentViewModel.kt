package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.domain.GetPokemonUseCase
import java.util.*

class MainFragmentViewModel (
    private val pokemonRepository: PokemonRepository,
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel()  {

    val list = pokemonRepository.getPokemons().cachedIn(viewModelScope)










    
}