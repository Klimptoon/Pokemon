package com.example.pokemon.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.data.network.models.PokemonInfo
import com.example.pokemon.domain.GetPokemonUseCase
import com.example.pokemon.util.Resourse
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonInfoViewModel (
    private val getPokemonUseCase: GetPokemonUseCase
    ) : ViewModel() {



    private var _pokemonInfo = MutableLiveData<PokemonInfo>()
    var pokemonInfo: LiveData<PokemonInfo> = _pokemonInfo


    fun getPokemonInfo(name: String) {
        viewModelScope.launch {
            val pokemon = getPokemonUseCase.getPokemon(name)
            val listOfTypes = mutableListOf<String>()
            val typeOfPokemon = pokemon.data?.types?.forEach {
                listOfTypes.add(it.type.name)
                Log.d("ff", listOfTypes.toString())
            }

            Log.d("ff", typeOfPokemon.toString())
            when(pokemon) {
                is Resourse.Success -> {
                    val poke = PokemonInfo(
                        height = pokemon.data!!.height,
                        weight = pokemon.data!!.weight,
                        number = pokemon.data.id,
                        pokemonName = pokemon.data.name,
                        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.data.id}.png",
                        type = listOfTypes
                    )
                    _pokemonInfo.value = poke
                }
            }
        }
    }

}