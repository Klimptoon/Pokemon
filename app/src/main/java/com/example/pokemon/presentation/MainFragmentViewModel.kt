package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.data.network.models.PokemonInfo
import com.example.pokemon.data.network.responses.Result
import com.example.pokemon.domain.GetPokemonUseCase
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainFragmentViewModel(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {


    private val _pokemonList = MutableLiveData<PagingData<Result>>()
    var pokemonList: LiveData<PagingData<Result>> = _pokemonList


    fun getPokemonList() {
        viewModelScope.launch {
            pokemonList = pokemonRepository.getPokemons()
        }
    }


}