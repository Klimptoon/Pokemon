package com.example.pokemon.domain

import com.example.pokemon.data.network.PokemonRepository
import com.example.pokemon.data.network.responses.Pokemon
import com.example.pokemon.util.Resourse

class GetPokemonUseCase(private val repository: PokemonRepository,) {


    suspend fun getPokemon(name: String) : Resourse<Pokemon> {
        return repository.getPokemonInfo(name)
    }

}