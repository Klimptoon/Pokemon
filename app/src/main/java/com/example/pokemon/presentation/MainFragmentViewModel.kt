package com.example.pokemon.presentation

import androidx.lifecycle.*
import androidx.paging.PagingData
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.data.network.responses.Result
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableLiveData<PagingData<Result>>()
    var pokemonList: LiveData<PagingData<Result>> = _pokemonList

    fun getPokemonList() {
        viewModelScope.launch {
            pokemonList = pokemonRepository.getPokemons()
        }
    }
}
