package com.example.pokemon.presentation

import com.example.pokemon.data.network.responses.Result

interface PokemonAdapterListener {

    fun onClickPokemon(result: Result)

}